package com.melisa.paradise.ui.screens.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimatable
import com.melisa.paradise.model.Dog
import com.melisa.paradise.ui.components.ItemDogCard
import com.melisa.paradise.ui.components.TopBar
import com.melisa.paradise.ui.navigation.EdenAppNavigationRoutes.AdoptyPetScreenRoute
import com.melisa.paradise.ui.navigation.EdenAppNavigationRoutes.DetailScreenRoute

@Composable
fun Home(navController:NavController, dogList: List<Dog>,
        toggleTheme: () -> Unit
) {
    LazyColumn {
        item {
            TopBar(
                onToggle = {
                   toggleTheme()
                },navController
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(dogList) {
            dogList.forEach {
                ItemDogCard(
                    it,
                    onItemClicked = { dog ->
                        navController.navigate(AdoptyPetScreenRoute)
                    }
                )
            }
        }
    }
}