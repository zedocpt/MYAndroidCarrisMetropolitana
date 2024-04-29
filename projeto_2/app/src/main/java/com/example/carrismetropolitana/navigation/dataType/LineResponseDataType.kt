package com.example.carrismetropolitana.navigation.dataType

import android.os.Bundle
import androidx.navigation.NavType
import com.example.carrismetropolitana.model.responseData.lines.LineUiModel
import com.google.gson.Gson

class LineResponseDataType : NavType<LineUiModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): LineUiModel? {
        return bundle.getParcelable(key)
    }
    override fun parseValue(value: String): LineUiModel {
        return Gson().fromJson(value, LineUiModel::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: LineUiModel) {
        bundle.putParcelable(key, value)
    }
}