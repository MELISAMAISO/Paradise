package com.melisa.paradise.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.ui.graphics.vector.ImageVector
import com.melisa.paradise.R

sealed class BottomNavigationRoutes(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
   object AdoptionScreen : BottomNavigationRoutes(
        AdoptionScreenNavigationRoutes.homeScreenRoute,
        Icons.Filled.Home,
        "Adopt"
    )
    object NotificationsScreen : BottomNavigationRoutes(
        EdenAppNavigationRoutes.notificationsScreenRoute,
        Icons.Filled.Notifications,
        "Notifications"
    )
    object VeterinarianScreen : BottomNavigationRoutes(
        EdenAppNavigationRoutes.veterinarianScreenRoute,
        Icons.Filled.VolunteerActivism,
        "Vet"
    )
    object Home : BottomNavigationRoutes(
        EdenAppNavigationRoutes.HomeScreenRoute,
        Icons.Filled.Home,
        "Adopt"
    )
    object Detail : BottomNavigationRoutes(
        EdenAppNavigationRoutes.DetailScreenRoute,
        Icons.Filled.CatchingPokemon,
        "Vet"
    )
    object DetailsScreen : BottomNavigationRoutes(
        AdoptionScreenNavigationRoutes.detailsScreenRoute,
        Icons.Filled.HeartBroken,
        "Vet"
    )
}
