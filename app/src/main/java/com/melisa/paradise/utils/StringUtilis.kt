package com.melisa.paradise.utils

fun String.containsUppercase() = any { it.isUpperCase() }

fun String.containsDigit() = any { it.isDigit() }

fun String.containsLowercase() = any { it.isLowerCase() }