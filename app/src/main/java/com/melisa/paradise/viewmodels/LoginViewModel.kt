package com.melisa.paradise.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melisa.paradise.auth.AuthenticationResult
import com.melisa.paradise.auth.AuthenticationService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class LoginUiState {
    LOADING,
    WRONG_CREDENTIALS,
    NETWORK_ERROR,
    SIGNED_OUT
}

interface LogInViewModel {
    val uiState: State<LoginUiState>

    fun authenticate(emailAddress: String, password: String, onSuccess: () -> Unit)

    fun removeErrorMessage()
}

class EdenLogInViewModel(
    private val authenticationService: AuthenticationService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(), LogInViewModel {
    private var _uiState = mutableStateOf(LoginUiState.SIGNED_OUT)
    override val uiState = _uiState as State<LoginUiState>

    override fun authenticate(emailAddress: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch(defaultDispatcher) {
            _uiState.value = LoginUiState.LOADING
            when (val result = authenticationService.signIn(emailAddress.trimEnd(), password)) {
                is AuthenticationResult.Success -> withContext(Dispatchers.Main) {
                    onSuccess()
                }
                is AuthenticationResult.Failure -> setUiStateForFailureType(result.failureType)
            }
        }
    }

    override fun removeErrorMessage() {
        if (_uiState.value == LoginUiState.WRONG_CREDENTIALS || _uiState.value == LoginUiState.NETWORK_ERROR) {
            _uiState.value = LoginUiState.SIGNED_OUT
        }
    }
    private fun setUiStateForFailureType(failureType: AuthenticationResult.FailureType) {
        _uiState.value = when (failureType) {
            AuthenticationResult.FailureType.InvalidEmail, AuthenticationResult.FailureType.InvalidPassword, AuthenticationResult.FailureType.InvalidCredentials, AuthenticationResult.FailureType.InvalidUser -> LoginUiState.WRONG_CREDENTIALS
            AuthenticationResult.FailureType.NetworkFailure -> LoginUiState.NETWORK_ERROR
            AuthenticationResult.FailureType.UserCollision, AuthenticationResult.FailureType.AccountCreation -> throw IllegalStateException("UserCollision or AccountCreation failure cannot occur during log-in flow.")
        }
    }
}