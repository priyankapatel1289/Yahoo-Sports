package com.example.yahoosportssoccer.data.repository.dataSourceImpl

import com.example.yahoosportssoccer.data.api.SoccerTeamService
import com.example.yahoosportssoccer.data.model.Team
import com.example.yahoosportssoccer.data.repository.dataSource.TeamRemoteDataSource
import retrofit2.Response

class TeamRemoteDataSourceImpl(
    private val soccerTeamService: SoccerTeamService
    ): TeamRemoteDataSource {

    override suspend fun getSoccerTeams(): Response<List<Team>> {
        return soccerTeamService.getSoccerTeams()
    }
}