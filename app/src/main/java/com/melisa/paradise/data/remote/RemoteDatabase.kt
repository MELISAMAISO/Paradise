package com.melisa.paradise.data.remote

import androidx.lifecycle.LiveData
import com.melisa.paradise.data.domain.EdenUser
import com.melisa.paradise.data.domain.IncidentReportInfo
import com.melisa.paradise.data.domain.NotificationInfo
import com.melisa.paradise.data.domain.PetInfo

interface RemoteDatabase {
    val petsAvailableForAdoption: LiveData<List<PetInfo>>
    fun listenForNotifications(currentUser: EdenUser): LiveData<List<NotificationInfo>>
    suspend fun sendRequestForAdoption(userId: String, petId: String)
    suspend fun saveIncidentReport(userId: String, email: String, reportInfo: IncidentReportInfo)
}