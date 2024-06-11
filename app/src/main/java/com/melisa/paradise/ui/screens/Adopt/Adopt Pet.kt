package com.melisa.paradise.ui.screens.Adopt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.melisa.paradise.R
import com.melisa.paradise.ui.components.OwnerCard
import com.melisa.paradise.ui.navigation.EdenAppNavigationRoutes.HomeScreenRoute
import com.melisa.paradise.ui.navigation.EdenAppNavigationRoutes.homeScreenRoute
import com.melisa.paradise.viewmodels.AdoptPetViewModel

@Composable
fun AdoptyPetScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current

        Image(
            painterResource(id = R.drawable.vet8)
            , contentDescription ="logo" , modifier = Modifier
                .clip(CircleShape)
                .size(300.dp))
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Adopt A Pet",
            fontSize = 50.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Magenta,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.LineThrough
        )

        var name by remember { mutableStateOf(TextFieldValue("")) }
        var type by remember { mutableStateOf(TextFieldValue("")) }
        var location by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "name of the pet to adopt *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = type,
            onValueChange = { type= it },
            label = { Text(text = "male/female*") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text(text = "location of adoptee *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            var petRepository =AdoptPetViewModel(navController,context)
            petRepository.adoptPet(name.text.trim(),type.text.trim(),
                location.text)
            navController.navigate(HomeScreenRoute)


        }) {
            Text(text = "Adopt A Pet")
        }
    }
}
