package com.example.yahoosportssoccer.domain.usecase

import com.example.yahoosportssoccer.data.model.Team
import com.example.yahoosportssoccer.data.util.Resource
import com.example.yahoosportssoccer.domain.repository.TeamRepository

class GetTeamStatsUseCase(private val teamRepository: TeamRepository) {

    suspend fun execute(): Resource<List<Team>> {
        return teamRepository.getTeamsStats()
    }
}