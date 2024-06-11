package com.melisa.paradise.data.domain

data class NotificationInfo(
    val id: String,
    val type: NotificationType,
    val header: String,
    val content: String,
    val urlString: String? = null
) {
    enum class NotificationType { ORDERS, APPOINTMENTS, NGO }
}