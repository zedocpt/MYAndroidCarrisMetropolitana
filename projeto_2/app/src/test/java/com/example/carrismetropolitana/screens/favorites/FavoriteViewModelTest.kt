package com.example.carrismetropolitana.screens.favorites


import com.example.carrismetropolitana.model.db.FavoriteDbModel
import com.example.carrismetropolitana.repositories.FakeCarrisMetropolitanaRepository
import com.example.carrismetropolitana.utils.MockMainDispatcherRule
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel
    private val repository = mockk<FakeCarrisMetropolitanaRepository>()

    @get:Rule
    val rule = MockMainDispatcherRule()

    @Test
    fun `test delete a Favorite and check is present on a favorite`() = runTest {
        // Arrange
        // Configurar o mock do repositório para retornar uma lista vazia de favoritos ao chamar getFavoritesWithFlow
        coEvery { repository.getFavoritesWithFlow() } returns flowOf(listOf())

        // Inicializar o ViewModel com o repositório mockado
        viewModel = FavoriteViewModel(repository)

        // Configurar o comportamento do método deleteAllFavorites no mock do repositório para apenas executar
        coEvery { repository.deleteAllFavorites() } just runs

        // Configurar o comportamento do método getFavorites no mock do repositório para retornar uma lista vazia de favoritos
        coEvery { repository.getFavorites() } returns arrayListOf()

        // Act
        // Obter o valor da lista de favoritos do ViewModel
        val favoriteResul = viewModel.favList.value

        // Assert
        // Verificar se a lista de favoritos está vazia
        assertEquals(favoriteResul, arrayListOf<FavoriteDbModel>())
    }

    @Test
    fun `test delete a Favorite and check if it is not present`() = runTest {
        val expectedFavorite = FavoriteDbModel(
            id = "1001",
            short_name = "1001",
            long_name = "Alfragide (Estr Seminario) - Reboleira (Estação)",
            color = "#C61D23",
            text_color = "#FFFFFF"
        )

        // Configurar o comportamento do método getFavoritesWithFlow no mock do repositório
        coEvery { repository.getFavoritesWithFlow() } returns flowOf(listOf(expectedFavorite))

        // Configurar uma resposta padrão para a função getFavById
        coEvery { repository.getFavById(any()) } returns null

        viewModel = FavoriteViewModel(repository)

        val deleteFavorite = FavoriteDbModel(
            id = "1106",
            short_name = "1106",
            long_name = "Queluz Baixo (Centro Comercial) - Algés (Estação)",
            color = "#C61D23",
            text_color = "#FFFFFF"
        )

        // Configurar o comportamento do método deleteFavorite no mock do repositório
        coEvery { repository.deleteFavorite(deleteFavorite.id) } just runs

        // Excluir o favorito
        viewModel.deleteFavorite(deleteFavorite.id)

        // Verificar se o favorito foi removido corretamente
        val favoriteResult = viewModel.getFavoriteByIdV2(deleteFavorite.id)
        assertNull(favoriteResult)

    }

    @Test
    fun `test check 1001 is present on a favorite`() = runTest {
        val expectedFavorite = FavoriteDbModel(
            id = "1001",
            short_name = "1001",
            long_name = "Alfragide (Estr Seminario) - Reboleira (Estação)",
            color = "#C61D23",
            text_color = "#FFFFFF"
        )

        coEvery { repository.getFavoritesWithFlow() } returns flowOf(listOf(expectedFavorite))

        viewModel = FavoriteViewModel(
            repository
        )

        val expectedFavoriteNotCorrect = FavoriteDbModel(
            id = "1002",
            short_name = "1001",
            long_name = "Alfragide (Estr Seminario) - Reboleira (Estação)",
            color = "#C61D23",
            text_color = "#FFFFFF"
        )

        coEvery { repository.getFavById(expectedFavorite.id) } returns expectedFavorite

        // Act
        val result = viewModel.getFavoriteByIdV2(expectedFavorite.id)

        // Assert
        assertNotEquals(expectedFavorite, expectedFavoriteNotCorrect)
        assertEquals(expectedFavorite, result)
    }

    @Test
    fun `insert check a favorite and check if it is present favorite`() = runTest {
        // Arrange
        // Definir o favorito esperado a ser inserido
        val expectedFavorite = FavoriteDbModel(
            id = "1002",
            short_name = "1002",
            long_name = "Alfragide (Estr Seminario) - Reboleira (Estação)",
            color = "#C61D23",
            text_color = "#FFFFFF"
        )

        // Configurar o mock do repositório para retornar uma lista contendo o favorito esperado ao chamar getFavoritesWithFlow
        coEvery { repository.getFavoritesWithFlow() } returns flowOf(listOf(expectedFavorite))

        // Inicializar o ViewModel com o repositório mockado
        viewModel = FavoriteViewModel(repository)

        // Act
        // Configurar o comportamento do mock do repositório para apenas executar a inserção do favorito
        coEvery { repository.insertFavorite(expectedFavorite) } just runs

        // Atuar: inserir o favorito esperado
        viewModel.insertFavorite(expectedFavorite)

        // Configurar o comportamento do mock do repositório para retornar o favorito esperado ao chamar getFavById com o id do favorito esperado
        coEvery { repository.getFavById(expectedFavorite.id) } returns expectedFavorite

        // Obter o resultado ao chamar getFavoriteByIdV2 com o id do favorito esperado
        val result = viewModel.getFavoriteByIdV2(expectedFavorite.id)

        // Assert
        // Verificar se o favorito inserido é igual ao favorito esperado
        assertEquals(expectedFavorite, result)
    }
}