package com.melisa.paradise.ui.screens.homescreen.adoptionscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.statusBarsPadding
import com.melisa.paradise.R
import com.melisa.paradise.data.domain.PetInfo
import com.melisa.paradise.ui.components.PetCarouselCard
import com.melisa.paradise.ui.navigation.EdenAppNavigationRoutes
import com.melisa.paradise.ui.navigation.EdenAppNavigationRoutes.DetailScreenRoute
import com.melisa.paradise.ui.navigation.EdenAppNavigationRoutes.HomeScreenRoute
import com.melisa.paradise.ui.navigation.EdenAppNavigationRoutes.veterinarianScreenRoute
import com.melisa.paradise.viewmodels.AdoptPetViewModel
import com.melisa.paradise.viewmodels.AdoptionScreenViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AdoptionScreen(navController: NavController){
    Column(verticalArrangement = Arrangement.Center,
            modifier=Modifier.fillMaxWidth(),
        horizontalAlignment =Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.brown),
            contentDescription ="logo" ,
            modifier = Modifier
                .clip(CircleShape)
                .size(140.dp))


        Text(
            text = "Welcome to Paradise Pet Adoption",
            color = Color.Magenta,
            fontFamily = FontFamily.Cursive,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = {
            navController.navigate(HomeScreenRoute)},
            shape= RoundedCornerShape(80.dp),
            modifier = Modifier
                .width(400.dp)
                .height(50.dp),
        ) {
            Text(text = "Adopt A Pet")
        }
        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = {
            navController.navigate(veterinarianScreenRoute)},
            shape= RoundedCornerShape(80.dp),
            modifier = Modifier
                .width(400.dp)
                .height(50.dp),
        ) {
            Text(text = "Consult the Vet")
        }
        Spacer(modifier = Modifier.height(100.dp))

        Button(
            onClick = {
                navController.navigate(DetailScreenRoute)
            },
            shape = RoundedCornerShape(80.dp),
            modifier = Modifier
                .width(400.dp)
                .height(50.dp),
        ) {
            Text(text = "Other Services")
        }

    }

}


  /*  viewmodel: AdoptionScreenViewModel,
    onItemClicked: (PetInfo) -> Unit
) {
    val featuredList by viewmodel.featuredList
    val recommendedList by viewmodel.recommendedList
    val currentlyAppliedFilter by viewmodel.currentlyAppliedFilter
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    Box {
        Column(modifier = Modifier.fillMaxSize()) {
            // chip Group
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .padding(start = 8.dp, top = 16.dp)
                    .statusBarsPadding()
                    .fillMaxWidth(),
            ) {
                AdoptionScreenViewModel.FilterOptions.entries.forEach { selectedFilter ->
                    FilterChip(
                        onClick = { viewmodel.applyFilter(selectedFilter) },
                        isSelected = currentlyAppliedFilter == selectedFilter,
                        content = {
                            Text(
                                text = selectedFilter.name
                                    .lowercase()
                                    .replaceFirstChar { it.uppercase() })
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            // featured pets - header
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Featured Pets",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onPrimary
            )
            // featured pets list
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
            ) {
                items(featuredList) { item ->
                    PetCarouselCard(
                        modifier = Modifier.size(200.dp),
                        petInfo = item,
                        onClick = { onItemClicked(item) }
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
            // recommended pets - header
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Recommended Pets",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onPrimary
            )
            // recommended pets - list
            val likedPetInfoMap = remember { mutableStateMapOf<Int, Boolean>() }
            LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                items(recommendedList) { petInfo ->
                    PetInfoCard(
                        petInfo = petInfo,
                        isLiked = likedPetInfoMap.getOrPut(petInfo.id.toInt()) { false },
                        onLikeButtonClicked = {
                            // if an entry exists in the map, negate it
                            // else create entry and set it to true
                            likedPetInfoMap[petInfo.id.toInt()] =
                                likedPetInfoMap[petInfo.id.toInt()]?.not() ?: true
                            coroutineScope.launch {
                                onLikeButtonClicked(
                                    snackBarHostState,
                                    viewmodel,
                                    petInfo,
                                    likedPetInfoMap.getValue(petInfo.id.toInt())
                                )
                            }
                        },
                        onClick = { onItemClicked(petInfo) }
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            }
        }
        SnackbarHost(
            modifier = Modifier.align(Alignment.BottomCenter),
            hostState = snackBarHostState
        )
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterChip(
    onClick: () -> Unit,
    isSelected: Boolean = false,
    outlinedBorderColor: Color = MaterialTheme.colors.secondary,
    selectedBackgroundColor: Color = MaterialTheme.colors.secondary,
    shape: Shape = CircleShape,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max),
        onClick = onClick,
        border = if (isSelected) null else BorderStroke(1.dp, outlinedBorderColor),
        color = if (isSelected) selectedBackgroundColor else MaterialTheme.colors.surface,
        shape = shape,
    ) {
        Row(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
        ) {
            if (isSelected) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 8.dp)
                        .size(16.dp),
                    imageVector = Icons.Filled.Done,
                    contentDescription = null
                )
            }
            content()
        }
    }
}

private suspend fun onLikeButtonClicked(
    hostState: SnackbarHostState,
    viewmodel: AdoptionScreenViewModel,
    petInfo: PetInfo,
    isPetFavourited: Boolean
) {
    hostState.currentSnackbarData?.dismiss()
    hostState.showSnackbar(if (isPetFavourited) "Added pet to favourites" else "Removed pet from favourites")
    if (isPetFavourited) viewmodel.addPetToFavourites(petInfo)
    else viewmodel.removePetFromFavourites(petInfo)
}


@ExperimentalMaterialApi
@Composable
private fun PetInfoCard(
    petInfo: PetInfo,
    isLiked: Boolean = false,
    onLikeButtonClicked: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.requiredHeight(80.dp),
        shape = MaterialTheme.shapes.small,
        onClick = onClick
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .weight(2f),
                painter = rememberImagePainter(
                    data = petInfo.imageResource,
                    builder = { crossfade(true) }
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
            Column(
                Modifier
                    .weight(4f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = petInfo.name,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = "${petInfo.type} | ${petInfo.breed}",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = petInfo.gender,
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 16.dp),
                onClick = onLikeButtonClicked
            ) {
                Icon(
                    imageVector = if (isLiked) Icons.Filled.Favorite
                    else Icons.Outlined.FavoriteBorder,
                    contentDescription = "",
                    tint = if (isLiked) MaterialTheme.colors.secondary
                    else Color.Gray
                )
            }
        }
    }
}*/



