package com.example.carrismetropolitana.screens.favorites


import androidx.compose.runtime.collectAsState
import com.example.carrismetropolitana.model.db.FavoriteDbModel
import com.example.carrismetropolitana.repositories.FakeCarrisMetropolitanaRepository
import com.example.carrismetropolitana.repository.CarrisMetropolitanaDbRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    private val repository = mockk<CarrisMetropolitanaDbRepository>()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        viewModel = FavoriteViewModel(
            dispatcher,
            repository
        )
    }

    @Test
    fun `test insert a favorite item`() = runTest {
        // Arrange
        val expectedFavorite = FavoriteDbModel(
            id = "1001",
            short_name = "1001",
            long_name = "Alfragide (Estr Seminario) - Reboleira (Estação)",
            color = "#C61D23",
            text_color = "#FFFFFF"
        )

        val expectedFavoriteNotCorrect = FavoriteDbModel(
            id = "1002",
            short_name = "1001",
            long_name = "Alfragide (Estr Seminario) - Reboleira (Estação)",
            color = "#C61D23",
            text_color = "#FFFFFF"
        )

        coEvery { repository.getFavoriteById("1001") } returns expectedFavorite

        // Act
        val result = viewModel.getFavoriteByIdV2(expectedFavorite.id)

        // Assert
        assertNotEquals(expectedFavorite, expectedFavoriteNotCorrect)
        assertEquals(expectedFavorite, result)
    }
}