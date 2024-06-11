package com.melisa.paradise.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PetInfo(
    val id:String,
    val name:String,
    val age:String,
    val color:String,
    val weight:String,
    val description:String,
    val type:String,
    val breed:String,
    val gender:String,
    val imageResource:String
): Parcelable