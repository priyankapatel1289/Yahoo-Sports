package com.example.yahoosportssoccer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yahoosportssoccer.domain.usecase.TeamStandingsUseCase

class TeamStandingsViewModelFactory(
    private val getTeamStandingsUseCase: TeamStandingsUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TeamStandingsViewModel(
            getTeamStandingsUseCase
        )as T
    }
}