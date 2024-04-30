package com.example.carrismetropolitana.screens.uiModel.lines

import com.example.carrismetropolitana.model.responseData.lines.LineResponseData


data class LineUiModel(
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

fun LineResponseData.toUiModel() = LineUiModel(
    color = color,
    id = id,
    localities = localities,
    long_name = long_name,
    municipalities = municipalities,
    patterns = patterns,
    routes = routes,
    short_name = short_name,
    text_color = text_color
)