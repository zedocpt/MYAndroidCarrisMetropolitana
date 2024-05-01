package com.example.carrismetropolitana.usesCase

import com.example.carrismetropolitana.data.DataOrException
import com.example.carrismetropolitana.repository.CarrisMetropolitanaRepository
import com.example.carrismetropolitana.model.responseData.wrapper.LinesWrapper
import javax.inject.Inject

class   GetLineMatchWithFavoritesUseCase @Inject constructor(private val carrisMetropolitanaRepository: CarrisMetropolitanaRepository) {

    suspend operator fun invoke(): DataOrException<ArrayList<LinesWrapper>, Boolean, Exception> {
        return carrisMetropolitanaRepository.getLinesMatchWithFavorites()
    }
}