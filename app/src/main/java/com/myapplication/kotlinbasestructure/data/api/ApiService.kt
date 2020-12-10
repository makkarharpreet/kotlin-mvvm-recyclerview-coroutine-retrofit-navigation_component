package com.myapplication.kotlinbasestructure.data.api

import com.myapplication.kotlinbasestructure.data.model.TracksApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getTracksApi(@Url url: String):TracksApiResponse
}