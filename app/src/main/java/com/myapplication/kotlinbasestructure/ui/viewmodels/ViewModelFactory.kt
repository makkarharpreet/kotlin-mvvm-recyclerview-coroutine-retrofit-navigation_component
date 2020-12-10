package com.myapplication.kotlinbasestructure.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapplication.kotlinbasestructure.data.repository.BaseRepository
import com.myapplication.kotlinbasestructure.data.repository.DashboardRepository

class ViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> DashboardViewModel(
                    repository as DashboardRepository
                    ) as T

            else -> throw IllegalArgumentException("Unknown class name")
        }
    }

}
