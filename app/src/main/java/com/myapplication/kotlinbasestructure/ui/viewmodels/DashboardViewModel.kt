package com.myapplication.kotlinbasestructure.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.myapplication.kotlinbasestructure.data.repository.DashboardRepository
import com.myapplication.kotlinbasestructure.network.Resource
import kotlinx.coroutines.Dispatchers

class DashboardViewModel (private val dashboardRepository: DashboardRepository) : ViewModel() {

    fun tracksApi(url: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(dashboardRepository.tracksApi(url)))
        } catch (exception: Exception) {
            emit(Resource.Failure(true, null,null))
        }
    }
}