package com.melisa.paradise.data.domain

import android.net.Uri

data class EdenUser(
    val id:String,
    val name: String?,
    val email: String?,
    val photoUrl: Uri?
)