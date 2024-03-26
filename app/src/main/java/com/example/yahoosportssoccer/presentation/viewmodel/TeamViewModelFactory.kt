package com.example.yahoosportssoccer.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yahoosportssoccer.domain.usecase.GetTeamStatsUseCase

class TeamViewModelFactory(
    private val app: Application,
    private val getTeamStatsUseCase: GetTeamStatsUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TeamViewModel(
            app,
            getTeamStatsUseCase
        ) as T
    }
}