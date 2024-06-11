package com.melisa.paradise.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color

@Composable
fun CircularLoadingProgressOverlay(
    modifier: Modifier = Modifier,
    overlayColor: Color = Color.Black,
    isOverlayVisible: Boolean,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content()
        if (isOverlayVisible) {
            Surface(
                modifier = modifier
                    .fillMaxSize()
                    .alpha(0.5f),
                color = overlayColor
            ) {
                Box {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}