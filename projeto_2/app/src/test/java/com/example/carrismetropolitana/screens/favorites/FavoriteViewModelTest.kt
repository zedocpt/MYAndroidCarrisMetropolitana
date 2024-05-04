package com.example.carrismetropolitana.screens.favorites


import com.example.carrismetropolitana.model.db.FavoriteDbModel
import com.example.carrismetropolitana.repositories.FakeCarrisMetropolitanaRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test


class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    private val repository = mockk<FakeCarrisMetropolitanaRepository>()
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

        coEvery { repository.getFavById("1001") } returns expectedFavorite

        // Act
        val result = viewModel.getFavoriteByIdV2(expectedFavorite.id)

        // Assert
        assertNotEquals(expectedFavorite, expectedFavoriteNotCorrect)
        assertEquals(expectedFavorite, result)
    }
}