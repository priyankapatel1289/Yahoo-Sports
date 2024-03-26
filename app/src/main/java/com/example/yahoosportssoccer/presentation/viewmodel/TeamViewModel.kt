package com.example.yahoosportssoccer.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yahoosportssoccer.data.model.Team
import com.example.yahoosportssoccer.data.util.Resource
import com.example.yahoosportssoccer.domain.usecase.GetTeamStatsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(
    private val app: Application,
    private val getTeamStatsUseCase: GetTeamStatsUseCase
): AndroidViewModel(app) {
    val teamStats : MutableLiveData<Resource<List<Team>>> = MutableLiveData()

    fun getTeamStats() {
        viewModelScope.launch(Dispatchers.IO) {
            teamStats.postValue(Resource.Loading())

            try {
                if (isNetworkAvailable(app)) {
                    teamStats.postValue(Resource.Loading())
                    val apiResult = getTeamStatsUseCase.execute()
                    teamStats.postValue(apiResult)
                } else {
                    teamStats.postValue(Resource.Error("Internet is not available"))
                }
            } catch (e: Exception) {
                teamStats.postValue(Resource.Error(e.message.toString()))

            }
        }
    }

    // This code is copied from Android dev resources as it is a very common practice
    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (networkCapabilities != null) {
                when {
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val networkInfo = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return networkInfo?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        }
        return false
    }


}