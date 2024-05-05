package com.example.carrismetropolitana.di

import android.content.Context
import androidx.room.Room
import com.example.carrismetropolitana.data.CarrisMetropolitanaDao
import com.example.carrismetropolitana.data.CarrisMetropolitanaDataBase
import com.example.carrismetropolitana.network.CarrisMetropolitanaApi
import com.example.carrismetropolitana.repository.CarrisMetropolitanaDbRepository
import com.example.carrismetropolitana.repository.ICarrisMetropolitanaDbRepository
import com.example.carrismetropolitana.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: CarrisMetropolitanaDao
    ) = CarrisMetropolitanaDbRepository(dao) as ICarrisMetropolitanaDbRepository

  /*  @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO*/

    @Singleton
    @Provides
    fun provideCarrisMetropolitanaDao(dataBase: CarrisMetropolitanaDataBase) : CarrisMetropolitanaDao =
        dataBase.carrisMetropolitanaDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : CarrisMetropolitanaDataBase
        = Room.databaseBuilder(context,
            CarrisMetropolitanaDataBase::class.java,
            "CarrisMetropolitana_dataBase").fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun providesOpenCarrisMetropolitanaApi() : CarrisMetropolitanaApi{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarrisMetropolitanaApi::class.java)
    }
}