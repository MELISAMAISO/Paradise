package com.melisa.paradise.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.melisa.paradise.R
import com.melisa.paradise.ui.components.EdenSingleLineTextField
import com.melisa.paradise.viewmodels.LogInViewModel
import com.melisa.paradise.viewmodels.LoginUiState

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    viewModel: LogInViewModel,
    onSuccessfulAuthentication:()->Unit
) {
    val uiState by viewModel.uiState
    var emailAddressText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    val isLoginButtonEnabled by remember(
        emailAddressText,
        passwordText
    ) { mutableStateOf(emailAddressText.isNotBlank() && passwordText.isNotEmpty()) }
    val keyboardController = LocalSoftwareKeyboardController.current
    // keyboard actions for the text fields
    val keyboardActions = KeyboardActions(onDone = {
        if (emailAddressText.isNotBlank() && passwordText.isNotEmpty()) {
            keyboardController?.hide()
            viewModel.authenticate(
                emailAddress = emailAddressText,
                password = passwordText,
                onSuccess = onSuccessfulAuthentication
            )
        }
    })

    LoginScreen(
        emailAddressText = emailAddressText,
        onEmailAddressTextChange = {
            viewModel.removeErrorMessage() // If there is an error message, clear it
            emailAddressText = it
        },
        passwordText = passwordText,
        onPasswordTextChange = {
            viewModel.removeErrorMessage() // If there is an error message, clear it
            passwordText = it
        },
        isPasswordVisible = isPasswordVisible,
        onPasswordVisibilityIconClick = { isPasswordVisible = !isPasswordVisible },
        onLoginButtonClick = {
            viewModel.authenticate(
                emailAddress = emailAddressText,
                password = passwordText,
                onSuccess = onSuccessfulAuthentication
            )
        },
        errorMessage = {
            Text(
                text = when (uiState) {
                    LoginUiState.NETWORK_ERROR -> stringResource(id = R.string.label_network_error_message)
                    LoginUiState.WRONG_CREDENTIALS -> stringResource(id = R.string.label_login_error_message)
                    else -> ""
                },
                color = MaterialTheme.colors.error
            )
        },
        keyboardActions = keyboardActions,
        isLoginButtonEnabled = isLoginButtonEnabled,
        isLoadingOverlayVisible = uiState == LoginUiState.LOADING,
        isErrorMessageVisible = uiState == LoginUiState.WRONG_CREDENTIALS || uiState == LoginUiState.NETWORK_ERROR,
    )
}

@Composable
fun LoginScreen(
    emailAddressText: String,
    onEmailAddressTextChange: (String) -> Unit,
    passwordText: String,
    onPasswordTextChange: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoginButtonEnabled: Boolean = true,
    isLoadingOverlayVisible: Boolean = false,
    isErrorMessageVisible: Boolean = false,
    errorMessage: @Composable () -> Unit = {},
    isPasswordVisible: Boolean = false,
    onPasswordVisibilityIconClick: () -> Unit = {},
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.vet4)
                , contentDescription ="logo" , modifier = Modifier
                    .clip(CircleShape)
                    .size(140.dp))
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier
                    .paddingFromBaseline(top = 184.dp),
                text = stringResource(id = R.string.label_login_with_email),
                style = MaterialTheme.typography.h1
            )

            Spacer(modifier = Modifier.padding(16.dp))

            EdenSingleLineTextField(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                value = emailAddressText,
                onValueChange = onEmailAddressTextChange,
                isError = isErrorMessageVisible,
                keyboardActions = keyboardActions,
                label = { Text(text = stringResource(R.string.placeholder_email_address)) },
            )

            Spacer(modifier = Modifier.padding(8.dp))

            EdenSingleLineTextField(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                value = passwordText,
                onValueChange = onPasswordTextChange,
                label = { Text(text = stringResource(R.string.placeholder_password)) },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = isErrorMessageVisible,
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable(onClick = onPasswordVisibilityIconClick),
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff,
                        contentDescription = ""
                    )
                },
                keyboardActions = keyboardActions,
            )
            if (isErrorMessageVisible) {
                Surface(
                    modifier = Modifier.align(Alignment.Start),
                    content = errorMessage
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

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
                onClick = onLoginButtonClick,
                shape = MaterialTheme.shapes.medium,
                content = {
                    Text(
                        text = stringResource(id = R.string.label_login),
                        fontWeight = FontWeight.Bold
                    )
                },
                enabled = isLoginButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.onSecondary
                )
            )


        }
        if (isLoadingOverlayVisible) LoadingProgressOverlay(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f)
        )
    }
}

@Composable
private fun LoadingProgressOverlay(modifier: Modifier) {
    Surface(
        modifier = modifier,
        color = Color.Black
    ) {
        Box {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
