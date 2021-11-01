package com.example.space_xapp.api

import com.example.space_xapp.data.Crew
import com.example.space_xapp.data.Info
import com.example.space_xapp.data.Ship
import retrofit2.http.GET

interface SpacexApi {

    companion object {
        const val BASE_URL = "https://api.spacexdata.com/v4/"
    }

    @GET("crew")
    suspend fun getCrewData(): List<Crew>

    @GET("ships")
    suspend fun getShipsDetails(): List<Ship>

    @GET("company")
    suspend fun getCompanyInfo() : Info

}