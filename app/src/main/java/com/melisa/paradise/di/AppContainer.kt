package com.melisa.paradise.di

import com.melisa.paradise.auth.AuthenticationService
import com.melisa.paradise.auth.FirebaseAuthenticationService
import com.melisa.paradise.data.EdenRepository
import com.melisa.paradise.data.Repository
import com.melisa.paradise.data.remote.FirebaseRemoteDatabase
import com.melisa.paradise.utils.AdoptionScreenViewModelFactory
import com.melisa.paradise.utils.LogInViewModelFactory
import com.melisa.paradise.utils.NotificationScreenViewModelFactory
import com.melisa.paradise.utils.ReportsScreenViewModelFactory
import com.melisa.paradise.utils.SignUpViewModelFactory
import kotlinx.coroutines.Dispatchers

class AppContainer {
    private val defaultDispatcher = Dispatchers.IO
    val authenticationService = FirebaseAuthenticationService() as AuthenticationService

    //repository
    private val remoteDatabase = FirebaseRemoteDatabase()
    private val repository = EdenRepository(remoteDatabase) as Repository

    val loginViewModelFactory = LogInViewModelFactory(authenticationService, defaultDispatcher)
    val signUpViewModelFactory = SignUpViewModelFactory(authenticationService, defaultDispatcher)
    val adoptionScreenViewModelFactory = AdoptionScreenViewModelFactory(repository, authenticationService, defaultDispatcher)
    val notificationScreenViewModelFactory = NotificationScreenViewModelFactory(repository,authenticationService)
    val reportScreenViewModelFactory = ReportsScreenViewModelFactory(repository,authenticationService,defaultDispatcher)
}