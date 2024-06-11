package com.melisa.paradise.auth

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.melisa.paradise.data.domain.EdenUser
import kotlinx.coroutines.tasks.await

class FirebaseAuthenticationService : AuthenticationService {

    private val firebaseAuth = FirebaseAuth.getInstance()
    override val currentUser get() = firebaseAuth.currentUser?.toEdenUser()
    override suspend fun signIn(
        email: String,
        password: String
    ): AuthenticationResult = runCatching {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
        AuthenticationResult.Success(firebaseAuth.currentUser!!.toEdenUser())
    }.getOrElse {
        AuthenticationResult.Failure(
            when (it) {
                is FirebaseAuthInvalidUserException -> AuthenticationResult.FailureType.InvalidEmail
                is FirebaseAuthInvalidCredentialsException -> AuthenticationResult.FailureType.InvalidPassword
                else -> AuthenticationResult.FailureType.NetworkFailure
            }
        )
    }

    override suspend fun createAccount(
        username: String,
        email: String,
        password: String,
        profilePhotoUri: Uri?
    ): AuthenticationResult = runCatching {
        val firebaseUser = firebaseAuth.createUser(username, email, password, profilePhotoUri)
        AuthenticationResult.Success(firebaseUser.toEdenUser())
    }.getOrElse {
        AuthenticationResult.Failure(
            when (it) {
                is FirebaseAuthWeakPasswordException -> AuthenticationResult.FailureType.InvalidPassword
                is FirebaseAuthInvalidCredentialsException -> AuthenticationResult.FailureType.InvalidCredentials
                is FirebaseAuthUserCollisionException -> AuthenticationResult.FailureType.UserCollision
                is FirebaseAuthInvalidUserException -> AuthenticationResult.FailureType.InvalidUser
                else -> AuthenticationResult.FailureType.NetworkFailure
            }
        )
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    private fun FirebaseUser.toEdenUser() = EdenUser(uid, displayName, email, photoUrl)

}