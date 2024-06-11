package com.melisa.paradise.data.domain

import android.graphics.Bitmap
import com.melisa.paradise.data.dto.IncidentReportInfoDTO

data class IncidentReportInfo(
    val address: String,
    val city: String,
    val state: String,
    val typeOfIncident: String,
    val description: String,
    val image: Bitmap
)

fun IncidentReportInfo.toIncidentReportInfoDTO(
    userID: String,
    email: String,
    imageURLString: String
) = IncidentReportInfoDTO(
    userID = userID,
    email = email,
    address = address,
    city = city,
    state = state,
    typeOfIncident = typeOfIncident,
    description = description,
    imageURL = imageURLString
)