package com.example.yahoosportssoccer.data.repository.dataSourceImpl

import com.example.yahoosportssoccer.data.model.Team
import com.example.yahoosportssoccer.data.repository.dataSource.TeamRemoteDataSource
import com.example.yahoosportssoccer.data.util.Resource
import com.example.yahoosportssoccer.domain.repository.TeamRepository
import retrofit2.Response

class TeamRepositoryImpl(
        private val teamRemoteDataSource: TeamRemoteDataSource
): TeamRepository {

    override suspend fun getTeamsStats(): Resource<List<Team>> {
        return responseToResource(teamRemoteDataSource.getSoccerTeams())
    }

    private fun responseToResource(response: Response<List<Team>>): Resource<List<Team>> {
        if (response.isSuccessful) {
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}