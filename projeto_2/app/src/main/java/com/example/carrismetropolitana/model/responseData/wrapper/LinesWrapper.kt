package com.example.carrismetropolitana.model.responseData.wrapper

import com.example.carrismetropolitana.model.responseData.lines.LineResponseData

data class LinesWrapper(
    var lineResponseData: LineResponseData,
    var isFavorite: Boolean = false
)




