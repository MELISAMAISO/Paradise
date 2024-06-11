package com.melisa.paradise.viewmodels

import android.net.Uri
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melisa.paradise.auth.AuthenticationResult
import com.melisa.paradise.auth.AuthenticationService
import com.melisa.paradise.utils.containsDigit
import com.melisa.paradise.utils.containsLowercase
import com.melisa.paradise.utils.containsUppercase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class SignUpUiFailureType {
    INVALID_CREDENTIALS,
    USER_COLLISION,
    NETWORK_ERROR,
}

sealed class SignUpUiState {
    object Loading : SignUpUiState()
    object SignedOut : SignUpUiState()
    data class Failed(val cause: SignUpUiFailureType) : SignUpUiState()
}

interface SignUpViewModel {
    val uiState: State<SignUpUiState>

    fun createNewAccount(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        profilePhotoUri: Uri? = null
    )

    fun removeErrorMessage()
}

class EdenSignUpViewModel(
    private val authenticationService: AuthenticationService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(), SignUpViewModel {
    private val _uiState = mutableStateOf<SignUpUiState>(SignUpUiState.SignedOut)
    override val uiState = _uiState as State<SignUpUiState>

    private fun isValidEmail(email: String) =
        email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(
        password: String
    ) =
        password.length >= 8 && password.containsUppercase() && password.containsLowercase() && password.containsDigit()

    override fun createNewAccount(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        profilePhotoUri: Uri?,
    ) {
        if (!isValidEmail(email) || !isValidPassword(password)) _uiState.value =
            SignUpUiState.Failed(SignUpUiFailureType.INVALID_CREDENTIALS)
        else viewModelScope.launch(defaultDispatcher) {
            _uiState.value = SignUpUiState.Loading
            val authenticationResult =
                authenticationService.createAccount(name, email.trim(), password, profilePhotoUri)
            when (authenticationResult) {
                is AuthenticationResult.Success -> withContext(Dispatchers.Main) {
                    onSuccess()
                }
                is AuthenticationResult.Failure -> _uiState.value =
                    getUiStateForFailureType(authenticationResult.failureType)
            }
        }
    }

    override fun removeErrorMessage() {
        if (_uiState.value is SignUpUiState.Failed) {
            _uiState.value = SignUpUiState.SignedOut
        }
    }

    /**
     * Helper method used to get an instance of the associated
     * [SignUpUiState] for the provided [failureType].
     */
    private fun getUiStateForFailureType(failureType: AuthenticationResult.FailureType): SignUpUiState =
        SignUpUiState.Failed(
            when (failureType) {
                AuthenticationResult.FailureType.InvalidPassword, AuthenticationResult.FailureType.InvalidCredentials, AuthenticationResult.FailureType.InvalidEmail, AuthenticationResult.FailureType.InvalidUser -> SignUpUiFailureType.INVALID_CREDENTIALS
                AuthenticationResult.FailureType.NetworkFailure -> SignUpUiFailureType.NETWORK_ERROR
                AuthenticationResult.FailureType.UserCollision, AuthenticationResult.FailureType.AccountCreation -> SignUpUiFailureType.USER_COLLISION
            }
        )

}