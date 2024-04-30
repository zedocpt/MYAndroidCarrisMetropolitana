package com.example.carrismetropolitana.model.responseData.lines

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class LineResponseData(
    val color: String,
    val id: String,
    val localities: List<String>,
    val long_name: String,
    val municipalities: List<String>,
    val patterns: List<String>,
    val routes: List<String>,
    val short_name: String,
    val text_color: String
)