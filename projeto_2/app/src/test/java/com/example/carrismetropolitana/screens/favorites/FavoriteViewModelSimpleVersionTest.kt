package com.example.carrismetropolitana.screens.favorites

import com.example.carrismetropolitana.model.db.FavoriteDbModel
import com.example.carrismetropolitana.repositories.FakeCarrisMetropolitanaRepository
import com.example.carrismetropolitana.utils.MockMainDispatcherRule

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteViewModelSimpleVersionTest {

    @get:Rule
    val rule = MockMainDispatcherRule()

    @Test
    fun `insert a favorite and check if it's present`() {
        runTest {
            // Arrange

            val fakeRepository = FakeCarrisMetropolitanaRepository()
            val viewModel = FavoriteViewModel(fakeRepository)

            val expectedFavorite = FavoriteDbModel(
                id = "1002",
                short_name = "1002",
                long_name = "Alfragide (Estr Seminario) - Reboleira (Estação)",
                color = "#C61D23",
                text_color = "#FFFFFF"
            )

            // Act
            viewModel.insertFavorite(expectedFavorite).join()

            // Assert
            val result = viewModel.getFavoriteByIdV2(expectedFavorite.id)
            assertEquals(expectedFavorite, result)
        }
    }

    @Test
    fun `delete a favorite and check if it's not present`() {
        runTest {

            val fakeRepository = FakeCarrisMetropolitanaRepository()
            val viewModel = FavoriteViewModel(fakeRepository)

            // Arrange
            val expectedFavorite = FavoriteDbModel(
                id = "1002",
                short_name = "1002",
                long_name = "Alfragide (Estr Seminario) - Reboleira (Estação)",
                color = "#C61D23",
                text_color = "#FFFFFF"
            )

            // Act
            viewModel.insertFavorite(expectedFavorite).join()
            viewModel.deleteAllFavorites().join()

            // Wait for the coroutine to finish
            val result = viewModel.getFavoriteByIdV2(expectedFavorite.id)

            // Assert
            assertEquals(null, result)
        }
    }

    @Test
    fun `check if a specific favorite is present`() {
        runTest {

            val fakeRepository = FakeCarrisMetropolitanaRepository()
            val viewModel = FavoriteViewModel(fakeRepository)

            // Arrange
            val expectedFavorite = FavoriteDbModel(
                id = "1002",
                short_name = "1002",
                long_name = "Alfragide (Estr Seminario) - Reboleira (Estação)",
                color = "#C61D23",
                text_color = "#FFFFFF"
            )
            viewModel.insertFavorite(expectedFavorite).join()

            // Act
            val result = viewModel.getFavoriteByIdV2(expectedFavorite.id)

            // Assert
            assertEquals(expectedFavorite, result)
        }
    }
}