package com.melisa.paradise.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melisa.paradise.auth.AuthenticationService
import com.melisa.paradise.data.Repository
import com.melisa.paradise.data.domain.IncidentReportInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface ReportScreenViewModel {
    fun sendReport(incidentReportInfo: IncidentReportInfo)
}

class EdenReportScreenViewModel(
    private val repository: Repository,
    private val authenticationService: AuthenticationService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(), ReportScreenViewModel {
    override fun sendReport(incidentReportInfo: IncidentReportInfo) {
        authenticationService.currentUser?.let { currentUser ->
            viewModelScope.launch(defaultDispatcher) {
                repository.sendIncidentReport(currentUser, incidentReportInfo)
            }
        }
    }
}