package com.melisa.paradise.ui.screens.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CorporateFare
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.insets.statusBarsPadding
import com.melisa.paradise.data.domain.NotificationInfo

@ExperimentalMaterialApi
@Suppress("FunctionName")
@Composable
fun NotificationsScreen(
    notifications: List<NotificationInfo>,
    onNotificationClicked: (NotificationInfo) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Text(text = "Notifications", style = MaterialTheme.typography.h3)
        Spacer(modifier = Modifier.padding(16.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(notifications) { notificationInfo ->
                NotificationCard(
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(),
                    notificationInfo = notificationInfo,
                    onClick = { onNotificationClicked(notificationInfo) }
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun NotificationCard(
    notificationInfo: NotificationInfo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Icon with rounded background
            Surface(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .size(48.dp),
                color = when (notificationInfo.type) {
                    NotificationInfo.NotificationType.ORDERS -> MaterialTheme.colors.secondary
                    NotificationInfo.NotificationType.APPOINTMENTS -> MaterialTheme.colors.error
                    NotificationInfo.NotificationType.NGO -> MaterialTheme.colors.secondaryVariant
                },
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    imageVector = when (notificationInfo.type) {
                        NotificationInfo.NotificationType.ORDERS -> Icons.Filled.ShoppingCart
                        NotificationInfo.NotificationType.APPOINTMENTS -> Icons.Filled.AccountBox
                        NotificationInfo.NotificationType.NGO -> Icons.Filled.CorporateFare
                    },
                    contentDescription = ""
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = notificationInfo.header,
                    style = MaterialTheme.typography.h1,
                    maxLines = 1
                )
                Text(
                    text = notificationInfo.content,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
