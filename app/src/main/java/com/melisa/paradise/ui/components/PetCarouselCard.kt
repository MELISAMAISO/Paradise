package com.melisa.paradise.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.melisa.paradise.data.domain.PetInfo

@ExperimentalMaterialApi
@Composable
fun PetCarouselCard(
    petInfo: PetInfo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrim = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = 0.0f,
        endY = 1000.0f
    )
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(
                    data = petInfo.imageResource,
                    builder = { crossfade(true) }
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )

            Spacer(
                modifier = Modifier
                    .background(scrim)
                    .fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                val textColor = if (isSystemInDarkTheme()) MaterialTheme.colors.onPrimary
                else MaterialTheme.colors.onSecondary
                petInfo.name?.let {
                    Text(
                        modifier = Modifier,
                        text = it,
                        style = MaterialTheme.typography.h1,
                        color = textColor,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    modifier = Modifier,
                    text = "${petInfo.type} | ${petInfo.breed}",
                    style = MaterialTheme.typography.caption,
                    color = textColor,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
