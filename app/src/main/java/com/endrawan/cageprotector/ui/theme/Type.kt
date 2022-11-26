package com.endrawan.cageprotector.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.endrawan.cageprotector.R

val Montserrat = FontFamily(
    Font(R.font.montserrat_black, FontWeight.Black),
//    Font(R.font.montserrat_black_italic, FontWeight.Black),
    Font(R.font.montserrat_bold, FontWeight.Bold),
//    Font(R.font.montserrat_bold_italic, FontWeight.Bold),
    Font(R.font.montserrat_extra_bold, FontWeight.ExtraBold),
//    Font(R.font.montserrat_extra_bold_italic, FontWeight.ExtraBold),
    Font(R.font.montserrat_extra_light, FontWeight.ExtraLight),
//    Font(R.font.montserrat_extra_light_italic, FontWeight.ExtraLight),
//    Font(R.font.montserrat_italic),
    Font(R.font.montserrat_light, FontWeight.Light),
//    Font(R.font.montserrat_light_italic, FontWeight.Light),
    Font(R.font.montserrat_medium, FontWeight.Medium),
//    Font(R.font.montserrat_medium_italic, FontWeight.Medium),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_semi_bold, FontWeight.SemiBold),
//    Font(R.font.montserrat_semi_bold_italic, FontWeight.SemiBold),
    Font(R.font.montserrat_thin, FontWeight.Thin),
//    Font(R.font.montserrat_thin_italic, FontWeight.Thin),
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h4 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    h5 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    ),
    body1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)