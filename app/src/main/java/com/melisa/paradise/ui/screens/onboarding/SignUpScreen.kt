package com.melisa.paradise.ui.screens.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.melisa.paradise.R
import com.melisa.paradise.ui.components.CircularLoadingProgressOverlay
import com.melisa.paradise.ui.components.EdenSingleLineTextField
import com.melisa.paradise.viewmodels.SignUpUiFailureType
import com.melisa.paradise.viewmodels.SignUpUiState
import com.melisa.paradise.viewmodels.SignUpViewModel

@ExperimentalComposeUiApi
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    onAccountCreatedSuccessfully: () -> Unit
) {
    val invalidCredentialsErrorMessage =
        stringResource(id = R.string.label_enter_valid_email_and_password)
    val userAlreadyExistsErrorMessage = stringResource(id = R.string.label_user_already_exists)
    val networkErrorMessage = stringResource(id = R.string.label_network_error_message)
    val uiState by viewModel.uiState
    var firstNameText by rememberSaveable { mutableStateOf("") }
    var lastNameText by rememberSaveable { mutableStateOf("") }
    var emailAddressText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }
    val isErrorMessageVisible by remember(uiState) { mutableStateOf(uiState is SignUpUiState.Failed) }
    val errorMessageText by remember(uiState) {
        mutableStateOf(
            when (uiState) {
                is SignUpUiState.Failed -> when ((uiState as SignUpUiState.Failed).cause) {
                    SignUpUiFailureType.INVALID_CREDENTIALS -> invalidCredentialsErrorMessage
                    SignUpUiFailureType.USER_COLLISION -> userAlreadyExistsErrorMessage
                    SignUpUiFailureType.NETWORK_ERROR -> networkErrorMessage
                }
                else -> ""
            }
        )
    }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    val isSignUpButtonEnabled by remember(
        firstNameText,
        lastNameText,
        emailAddressText,
        passwordText
    ) {
        mutableStateOf(firstNameText.isNotBlank() && lastNameText.isNotBlank() && emailAddressText.isNotBlank() && passwordText.isNotEmpty())
    }

    // states for keyboard
    val keyboardController = LocalSoftwareKeyboardController.current
    //keyboard action object that is common to all text fields
    val keyboardActions = KeyboardActions(onDone = {
        if (firstNameText.isNotBlank() && lastNameText.isNotBlank() && emailAddressText.isNotBlank() && passwordText.isNotEmpty()) {
            keyboardController?.hide()
            viewModel.createNewAccount(
                name = "$firstNameText $lastNameText",
                email = emailAddressText,
                password = passwordText,
                onSuccess = onAccountCreatedSuccessfully
            )
        }
    })


    SignUpScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        firstNameText = firstNameText,
        onFirstNameTextChange = { firstNameText = it },
        lastNameText = lastNameText,
        onLastNameTextChange = { lastNameText = it },
        emailAddressText = emailAddressText,
        onEmailAddressTextChange = {
            viewModel.removeErrorMessage() // if there is an error message, clear it
            emailAddressText = it
        },
        passwordText = passwordText,
        onPasswordTextChange = {
            viewModel.removeErrorMessage()// if there is an error message, clear it
            passwordText = it
        },
        errorMessageText = errorMessageText,
        isErrorMessageVisible = isErrorMessageVisible,
        isPasswordVisible = isPasswordVisible,
        onPasswordVisibilityIconClick = { isPasswordVisible = !isPasswordVisible },
        isLoading = uiState is SignUpUiState.Loading,
        isSignUpButtonEnabled = isSignUpButtonEnabled,
        onSignUpButtonClick = {
            viewModel.createNewAccount(
                name = "$firstNameText $lastNameText",
                email = emailAddressText,
                password = passwordText,
                onSuccess = onAccountCreatedSuccessfully
            )
        },
        keyboardActions = keyboardActions
    )

}

@Composable
fun SignUpScreen(
    firstNameText: String,
    onFirstNameTextChange: (String) -> Unit,
    lastNameText: String,
    onLastNameTextChange: (String) -> Unit,
    emailAddressText: String,
    onEmailAddressTextChange: (String) -> Unit,
    passwordText: String,
    onPasswordTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessageText: String = "",
    isErrorMessageVisible: Boolean = false,
    isPasswordVisible: Boolean = false,
    onPasswordVisibilityIconClick: () -> Unit = {},
    isLoading: Boolean = false,
    isSignUpButtonEnabled: Boolean = true,
    onSignUpButtonClick: () -> Unit = {},
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    CircularLoadingProgressOverlay(isOverlayVisible = isLoading) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .paddingFromBaseline(top = 184.dp),
                text = stringResource(id = R.string.label_signup_for_new_account),
                style = MaterialTheme.typography.h1
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Row(modifier = Modifier.fillMaxWidth()) {

                EdenSingleLineTextField(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth(0.5f),
                    value = firstNameText,
                    onValueChange = onFirstNameTextChange,
                    label = { Text(text = stringResource(id = R.string.placeholder_first_name)) },
                    keyboardActions = keyboardActions,
                )

                Spacer(modifier = Modifier.padding(8.dp))

                EdenSingleLineTextField(
                    modifier = Modifier.height(56.dp),
                    value = lastNameText,
                    onValueChange = onLastNameTextChange,
                    label = { Text(text = stringResource(id = R.string.placeholder_last_name)) },
                    keyboardActions = keyboardActions
                )

            }

            Spacer(modifier = Modifier.padding(8.dp))

            EdenSingleLineTextField(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                value = emailAddressText,
                onValueChange = onEmailAddressTextChange,
                label = { Text(text = stringResource(id = R.string.placeholder_email_address)) },
                keyboardActions = keyboardActions,
                isError = isErrorMessageVisible
            )

            Spacer(modifier = Modifier.padding(8.dp))

            EdenSingleLineTextField(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                value = passwordText,
                onValueChange = onPasswordTextChange,
                label = { Text(text = stringResource(id = R.string.placeholder_password)) },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable(onClick = onPasswordVisibilityIconClick),
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff,
                        contentDescription = ""
                    )
                },
                keyboardActions = keyboardActions,
                isError = isErrorMessageVisible
            )

            if (isErrorMessageVisible) {
                Text(
                    text = errorMessageText,
                    color = MaterialTheme.colors.error
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .paddingFromBaseline(top = 24.dp),
                text = stringResource(id = R.string.label_terms_and_conditions),
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Button(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                onClick = onSignUpButtonClick,
                shape = MaterialTheme.shapes.medium,
                content = {
                    Text(
                        text = stringResource(id = R.string.label_signup),
                        fontWeight = FontWeight.Bold
                    )
                },
                enabled = isSignUpButtonEnabled,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            )
        }
    }
}