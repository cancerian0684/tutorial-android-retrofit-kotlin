package com.shunya.myapplication.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {

    fun loadData() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val api = ApiClient.createService(EmployeeApi::class.java)
        val response = api.getPerson()
        if(response.isSuccessful) {
            emit(Resource.success(response.body()?.data))
        }
    }
}
