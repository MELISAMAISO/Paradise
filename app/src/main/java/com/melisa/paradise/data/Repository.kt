package com.melisa.paradise.data

import androidx.lifecycle.LiveData
import com.melisa.paradise.data.domain.EdenUser
import com.melisa.paradise.data.domain.IncidentReportInfo
import com.melisa.paradise.data.domain.NotificationInfo
import com.melisa.paradise.data.domain.PetInfo
import com.melisa.paradise.data.remote.RemoteDatabase

interface Repository {
    val petsAvailableForAdoption: LiveData<List<PetInfo>>

    fun listenForNotifications(currentUser: EdenUser): LiveData<List<NotificationInfo>>
    suspend fun sendRequestForAdoption(user: EdenUser, petInfo: PetInfo)
    suspend fun sendIncidentReport(user: EdenUser, reportInfo: IncidentReportInfo)
}

class EdenRepository(
    private val remoteDatabase: RemoteDatabase
) : Repository {
    override val petsAvailableForAdoption: LiveData<List<PetInfo>> =
        remoteDatabase.petsAvailableForAdoption

    override fun listenForNotifications(currentUser: EdenUser) =
        remoteDatabase.listenForNotifications(currentUser)

    override suspend fun sendRequestForAdoption(user: EdenUser, petInfo: PetInfo) {
        petInfo.id?.let { remoteDatabase.sendRequestForAdoption(user.id, it) }
    }

    override suspend fun sendIncidentReport(user: EdenUser, reportInfo: IncidentReportInfo) {
        if (user.email == null) throw IllegalStateException("Incident report not sent. User email is null.")
        else remoteDatabase.saveIncidentReport(user.id, user.email, reportInfo)
    }
}