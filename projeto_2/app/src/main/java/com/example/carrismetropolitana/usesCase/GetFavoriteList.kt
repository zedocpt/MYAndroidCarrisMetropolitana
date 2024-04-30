package com.example.carrismetropolitana.usesCase

import com.example.carrismetropolitana.model.Favorite
import com.example.carrismetropolitana.repository.CarrisMetropolitanaDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteList @Inject constructor(private val carrisMetropolitanaDbRepository: CarrisMetropolitanaDbRepository) {

    operator fun invoke(): Flow<List<Favorite>> {
        return carrisMetropolitanaDbRepository.getFavorite()
    }
}