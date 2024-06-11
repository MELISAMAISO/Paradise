package com.melisa.paradise.auth

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

suspend fun FirebaseAuth.createUser(
    name: String,
    email: String,
    password: String,
    profilePhotoUri: Uri?
): FirebaseUser = runCatching {
    createUserWithEmailAndPassword(email, password).await()
    //if user is created successfully, set the display name and profile picture
    val userProfileChangeRequest = UserProfileChangeRequest.Builder()
        .setDisplayName(name)
        .setPhotoUri(profilePhotoUri)
        .build()
    currentUser!!.updateProfile(userProfileChangeRequest).await()
    currentUser!!
}.getOrThrow()