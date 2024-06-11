package com.melisa.paradise.auth

import android.net.Uri
import com.melisa.paradise.data.domain.EdenUser

interface AuthenticationService {
    val currentUser: EdenUser?
    suspend fun signIn(email: String, password: String): AuthenticationResult
    suspend fun createAccount(
        username: String,
        email: String,
        password: String,
        profilePhotoUri: Uri? = null
    ): AuthenticationResult

    fun signOut()
}

sealed class AuthenticationResult {
    data class Success(val user: EdenUser) : AuthenticationResult()
    data class Failure(val failureType: FailureType) : AuthenticationResult()
    enum class FailureType {
        InvalidEmail,
        InvalidPassword,
        InvalidCredentials,
        UserCollision,
        AccountCreation,
        InvalidUser,
        NetworkFailure
    }
}