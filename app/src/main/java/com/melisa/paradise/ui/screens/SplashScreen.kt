package com.melisa.paradise.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.melisa.paradise.R
import com.melisa.paradise.ui.navigation.AdoptionScreenNavigationRoutes.homeScreenRoute
import com.melisa.paradise.ui.navigation.EdenAppNavigationRoutes.AdoptyPetScreenRoute
import com.melisa.paradise.ui.navigation.EdenAppNavigationRoutes.DetailScreenRoute
import com.melisa.paradise.ui.navigation.EdenAppNavigationRoutes.HomeScreenRoute
import com.melisa.paradise.ui.navigation.OnBoardingNavigationRoutes.welcomeScreenRoute
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale= remember {
        Animatable(0f)
    }
    // AnimationEffect
    LaunchedEffect(key1=true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        navController.navigate(welcomeScreenRoute)
    }

    // Image
    Box(contentAlignment= Alignment.Center,
        modifier= Modifier
            .fillMaxSize()
            .background(Color.White))
    {
        Column {
            Text(text = "Make A New Friend",
                color = Color.Black,
                fontWeight = FontWeight.Bold)
        }
        Image(painter= painterResource(id= R.drawable.orange_dog),
        contentDescription="Logo",
        modifier= Modifier
            .scale(scale.value)
            .clip(CircleShape))
    }
}