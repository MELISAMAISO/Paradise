package com.melisa.paradise.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.melisa.paradise.auth.AuthenticationService
import com.melisa.paradise.data.Repository
import com.melisa.paradise.data.domain.NotificationInfo

interface NotificationsScreenViewModel {
    val notificationList: LiveData<List<NotificationInfo>>
}

class EdenNotificationsScreenViewmodel(
    repository: Repository,
    authenticationService: AuthenticationService
) : ViewModel(), NotificationsScreenViewModel {

    override val notificationList: LiveData<List<NotificationInfo>> =
        repository.listenForNotifications(authenticationService.currentUser!!)


}
