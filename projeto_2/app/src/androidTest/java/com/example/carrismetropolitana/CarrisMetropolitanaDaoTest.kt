package com.example.carrismetropolitana

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.filters.SmallTest
import com.example.carrismetropolitana.data.CarrisMetropolitanaDao
import com.example.carrismetropolitana.data.CarrisMetropolitanaDataBase
import com.example.carrismetropolitana.model.db.FavoriteDbModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch


@RunWith(AndroidJUnit4::class)
@SmallTest
class CarrisMetropolitanaDaoTest {

    private lateinit var dataBase: CarrisMetropolitanaDataBase
    private lateinit var carrisMetropolitanaDao: CarrisMetropolitanaDao

    @Before
    fun setupDataBase() {
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CarrisMetropolitanaDataBase::class.java
        ).allowMainThreadQueries().build()

        carrisMetropolitanaDao = dataBase.carrisMetropolitanaDao()
    }

    @After
    fun closeDataBase() {
        dataBase.close()
    }

    @Test
    fun insertAFavoriteAndReturnTrue() = runBlocking {
        val favoriteDbModel = FavoriteDbModel(
            id = "1001",
            short_name = "1001",
            long_name = "Alfragide (Estr Seminario) - Reboleira (Estação)",
            color = "#C61D23",
            text_color = "#FFFFFF"
        )
        carrisMetropolitanaDao.insertFavorite(favoriteDbModel)
        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            carrisMetropolitanaDao.getFavoritesWithFlow().collect {
                Assert.assertEquals(it[0].id, favoriteDbModel.id)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun updateFavoriteAnDReturnsTrue() = runBlocking {
        //insert word
        val favoriteDbModel = FavoriteDbModel(
            id = "1001",
            short_name = "1001",
            long_name = "Alfragide (Estr Seminario) - Reboleira (Estação)",
            color = "#C61D23",
            text_color = "#FFFFFF"
        )

        carrisMetropolitanaDao.insertFavorite(favoriteDbModel)

        // create updated word
        val updatedFavorite = FavoriteDbModel(
            id = "1001",
            short_name = "1001",
            long_name = "Linda a Velha",
            color = "#C61D23",
            text_color = "#FFFFFF"
        )

        // update
        carrisMetropolitanaDao.updateFavorite(updatedFavorite)

        // get word and assert if it equals to updated word
        val result = carrisMetropolitanaDao.getFavById(updatedFavorite.id)
        Assert.assertEquals(result.long_name, updatedFavorite.long_name);
    }

    @Test
    fun delete_returnsTrue() = runBlocking {
        val favoriteDbModel = FavoriteDbModel(
            id = "1001",
            short_name = "1001",
            long_name = "Alfragide (Estr Seminario) - Reboleira (Estação)",
            color = "#C61D23",
            text_color = "#FFFFFF"
        )

        carrisMetropolitanaDao.insertFavorite(favoriteDbModel)
        carrisMetropolitanaDao.deleteFavorite(favoriteDbModel.id)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            val list = carrisMetropolitanaDao.getFavorites()
            assertThat(list).doesNotContain(favoriteDbModel)
            latch.countDown()
        }
        latch.await()
        job.cancelAndJoin()

    }
}