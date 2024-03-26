package com.example.yahoosportssoccer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yahoosportssoccer.data.model.Team
import com.example.yahoosportssoccer.data.model.TeamStandings
import com.example.yahoosportssoccer.domain.usecase.TeamStandingsUseCase

class TeamStandingsViewModel(
    private val getTeamStandingsUseCase: TeamStandingsUseCase
): ViewModel() {
    private val teamStandings: MutableLiveData<List<TeamStandings>> = MutableLiveData()
    private val singleTeamStandings: MutableLiveData<List<TeamStandings>> = MutableLiveData()

    fun calculateTeamStandings(team: List<Team>): List<TeamStandings> {
        val standings = getTeamStandingsUseCase.execute(team)
        teamStandings.value = standings
        return standings
    }

    fun calculateIndividualTeamStandings(team: List<Team>, teamId: String): List<TeamStandings> {
        val games = getTeamStandingsUseCase.executeSingleTeam(team, teamId)
        singleTeamStandings.value = games
        return games
    }

}