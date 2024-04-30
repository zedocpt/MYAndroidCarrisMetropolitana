package com.example.carrismetropolitana.usesCase

import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.repository.CarrisMetropolitanaRepository
import com.example.carrismetropolitana.screens.showLines.entities.LinesWrapperUiModel
import javax.inject.Inject

class GetLineMatchWithFavorites @Inject constructor(private val carrisMetropolitanaRepository: CarrisMetropolitanaRepository) {

    suspend operator fun invoke(): DataOrException<ArrayList<LinesWrapperUiModel>, Boolean, Exception> {
        return carrisMetropolitanaRepository.GetLineMatchWithFavorites()
    }
}