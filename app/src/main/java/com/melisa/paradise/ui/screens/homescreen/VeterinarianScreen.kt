package com.melisa.paradise.ui.screens.homescreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.melisa.paradise.R
import com.melisa.paradise.data.domain.ImageWithText
import com.melisa.paradise.ui.navigation.AdoptionScreenNavigationRoutes.homeScreenRoute

@Composable
fun VeterinarianScreen(navController: NavController) {
    val context = LocalContext.current
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(top = 20.dp),
    ) {
        TopBar(
            name = "VET_MEL",
            modifier = Modifier
                .padding(10.dp), navController = navController)
        Spacer(modifier = Modifier.height(5.dp))
        ProfileSection(navController)
        Spacer(modifier = Modifier.height(25.dp))
        ButtonSection(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        HighlightsSection(highlights = listOf(
            ImageWithText(
                image= painterResource(id = R.drawable.youtube),text="YouTube"),
            ImageWithText(
                image= painterResource(id = R.drawable.whatsapp),text="WhatsApp"),
            ImageWithText(
                image= painterResource(id = R.drawable.sender),text="Telegram")
        ),
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 2.dp)
                .fillMaxWidth()
                .clickable {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey,download this app!")
                    context.startActivity(shareIntent)
                }
        )
        Spacer(modifier = Modifier.height(10.dp))
        PostTabView(imageWithTexts = listOf(
            ImageWithText(
                image = painterResource(id = R.drawable.grid),
                text = "Posts"
            ),
            ImageWithText(
                image = painterResource(id = R.drawable.palstv),
                text = "PalsTv"
            ),
            ImageWithText(
                image = painterResource(id = R.drawable.profile),
                text = "Profile"
            ),
        )
        ) {
            selectedTabIndex=it
        }
        when(selectedTabIndex){
            0-> PostSection(posts = listOf(
                painterResource(id = R.drawable.vet1),
                painterResource(id = R.drawable.vet2),
                painterResource(id = R.drawable.vet3),
                painterResource(id = R.drawable.vet4),
                painterResource(id = R.drawable.vet5),
                painterResource(id = R.drawable.vet6),
                painterResource(id = R.drawable.vet7),
                painterResource(id = R.drawable.vet8),
                painterResource(id = R.drawable.vet9),
                painterResource(id = R.drawable.vet1),
                painterResource(id = R.drawable.cat),
                painterResource(id = R.drawable.vet4),
                painterResource(id = R.drawable.yellow_dog),

            ),
                modifier= Modifier.fillMaxWidth()

            )
        }


    }
}

@Composable
fun TopBar(
    name:String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
    ){
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription ="back",
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = { navController.navigate(homeScreenRoute) })

        )
        Text(text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,)


        Icon(
            painter = painterResource(id = R.drawable.notifications),
            contentDescription = "back",
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = { navController.navigate(homeScreenRoute) })
        )

        Icon(imageVector = Icons.Default.MoreVert,
            contentDescription ="more",
            modifier = Modifier
                .size(24.dp)
                .clickable { }
        )
    }
}

@Composable
fun ProfileSection(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Column(modifier=modifier.fillMaxWidth()) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ){
            RoundImage(image = painterResource(id = R.drawable.doctor_mel),
                modifier= Modifier
                    .size(100.dp)
                    .weight(3f)
                    .clickable(onClick = {})
            )
            Spacer(modifier = Modifier.width(16.dp))
            StatSection(modifier = Modifier.weight(7f))

        }
        ProfileDescription(
            displayName = "Top Notch Veterinarian Doctor",
            description ="Vet_Mel,one of our best veterinaries.Have a sick pet?" +
                    "Hit the email button and have one on one talk with her." +
                    "Subscribe to our email channel and get to know different ways of taking care" +
                    "of your pet",
            url = "https://youtube.com/c/Doctor_Mel",
            reportedBy = listOf("leen","Samantha_Wills") ,
            otherCount = 20
        )

    }

}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier,

    ) {
    Image(painter = image, contentDescription =null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )

}

@Composable
fun StatSection(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
    ) {
        ProfileStat(numberText = "601", text ="Pets saved" )
        ProfileStat(numberText = "402", text ="Found new owners" )
        ProfileStat(numberText = "74", text ="Reports" )
    }

}

@Composable
fun ProfileStat(
    numberText:String,
    text:String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(5.dp)
    ) {
        Text(text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = text)
    }

}

@Composable
fun ProfileDescription(
    displayName:String,
    description:String,
    url:String,
    reportedBy:List<String>,
    otherCount:Int
) {
    val letterSpacing=0.7.sp
    val lineHeight=20.sp
    Column (
        modifier= Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ){
        Text(text = displayName,
            fontWeight = FontWeight.Bold,
            letterSpacing=letterSpacing,
            lineHeight=lineHeight
        )
        Text(text = description,
            letterSpacing=letterSpacing,
            lineHeight=lineHeight
        )
        Text(text = url,
            color= Color(0xFF3D3D91),
            letterSpacing=letterSpacing,
            lineHeight=lineHeight
        )
        if (reportedBy.isNotEmpty()){
            Text(
                text = buildString {
                    val boldStyle= SpanStyle(
                        color= Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    append("Reported by")
                    reportedBy.forEachIndexed { index, name->
                        (boldStyle)
                        append(name)
                        if (index<reportedBy.size -1){
                            append("","")
                        }
                    }
                    if (otherCount>2){
                        append("and")
                        (boldStyle)
                        append("$otherCount others")
                    }
                },
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }

    }

}

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val minwidth=65.dp
    val height=40.dp
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ){
        ActionButton(
            text = "Reports",
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .defaultMinSize(minwidth)
                .height(height)
                .clickable {
                }
        )
        ActionButton(
            text = "Quick Dial",
            modifier = Modifier
                .defaultMinSize(minwidth)
                .height(height)
                .clickable {
                    val phone = "+24700631"
                    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
                    context.startActivity(intent)
                }
        )
        ActionButton(
            text = "Email",
            modifier = Modifier
                .defaultMinSize(minwidth)
                .height(height)
                .clickable {
                    val emailIntent =
                        Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "abc@gmail.com", null))
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
                    context.startActivity(Intent.createChooser(emailIntent, "Send email.."))
                },

            )
        ActionButton(
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .defaultMinSize(minwidth)
                .height(height)
                .clickable { },
        )
    }
}


@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String?=null,
    icon: ImageVector?=null
) {
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(6.dp)
    ){
        if (text!=null){
            Text(text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
        if(icon!=null){
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint= Color.Black
            )
        }
    }

}

@Composable
fun HighlightsSection(
    modifier: Modifier = Modifier,
    highlights: List<ImageWithText>
) {
    LazyRow(modifier = Modifier) {
        items(highlights.size){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .padding(end = 60.dp)
            ) {
                RoundImage(image = highlights[it].image,
                    modifier=modifier
                        .size(60.dp),

                    )
                Text(text = highlights[it].text,
                    overflow = TextOverflow.Ellipsis,
                    textAlign= TextAlign.Center)
            }
        }
    }

}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    imageWithTexts:List<ImageWithText>,
    onTabSelected:(selectedIndex:Int)->Unit
) {
    var selectedTabIndex by remember{
        mutableStateOf(0)
    }
    val inactiveColor= Color(0xFF777777)
    TabRow(selectedTabIndex = selectedTabIndex,
        modifier.background(Color.Transparent),
        contentColor = Color.Black,
    ) {
        imageWithTexts.forEachIndexed{index, item ->

            Tab(selected =selectedTabIndex==index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex=index
                    onTabSelected(index)
                }) {
                Icon(painter = item.image,
                    contentDescription =item.text,
                    tint=if (selectedTabIndex==index) Color.Black else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp))
            }

        }
    }

}

@Composable
fun PostSection(
    posts:List<Painter>,
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(columns = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)) {
        items(posts.size){
            Image(painter = posts[it],
                contentDescription =null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
                    .clickable {}
            )
        }

    }

}



@Preview
@Composable
private fun prev() {
    VeterinarianScreen(rememberNavController())

}



