package com.melisa.paradise.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.melisa.paradise.R

val NunitoSans = FontFamily(
    Font(R.font.nunito_sans_bold, FontWeight.Bold),
    Font(R.font.nunito_sans_light, FontWeight.Light),
    Font(R.font.nunito_sans_semi_bold, FontWeight.SemiBold)
)

val Typography = Typography(
    defaultFontFamily = NunitoSans,
    h1 = TextStyle(fontSize = 18.sp, letterSpacing = 0.sp,fontWeight = FontWeight.Bold),
    h2 = TextStyle(fontSize = 14.sp, letterSpacing = 0.15.sp , fontWeight = FontWeight.Bold),
    subtitle1 = TextStyle(fontSize = 16.sp, letterSpacing = 0.sp,fontWeight = FontWeight.Light),
    body1 = TextStyle(fontSize = 14.sp, letterSpacing = 0.sp,fontWeight = FontWeight.Light),
    body2 = TextStyle(fontSize = 12.sp, letterSpacing = 0.sp,fontWeight = FontWeight.Light),
    button = TextStyle(fontSize = 14.sp, letterSpacing = 1.sp,fontWeight = FontWeight.SemiBold),
    caption = TextStyle(fontSize = 12.sp, letterSpacing = 0.sp,fontWeight = FontWeight.SemiBold)
)


