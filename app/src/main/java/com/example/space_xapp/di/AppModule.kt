package com.example.space_xapp.di

import android.app.Application
import androidx.room.Room
import com.example.space_xapp.api.SpacexApi
import com.example.space_xapp.data.SpacexDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(SpacexApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideSpacexApi(retrofit: Retrofit): SpacexApi =
        retrofit.create(SpacexApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): SpacexDatabase =
        Room.databaseBuilder(app, SpacexDatabase::class.java, "spacexdb")
            .build()

}