package com.myapplication.kotlinbasestructure.data.repository

import com.myapplication.kotlinbasestructure.data.api.ApiService

class DashboardRepository (private val apiService: ApiService): BaseRepository() {

    suspend fun tracksApi(url: String) = apiService.getTracksApi(url)
}