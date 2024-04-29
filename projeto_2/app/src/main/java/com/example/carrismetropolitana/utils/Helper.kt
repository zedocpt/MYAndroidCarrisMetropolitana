package com.example.carrismetropolitana.utils

import androidx.compose.ui.graphics.Color

fun hexStringToColor(hexString: String): Color {
    return Color(android.graphics.Color.parseColor(hexString))
}