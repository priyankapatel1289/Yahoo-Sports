package com.example.yahoosportssoccer.data.repository.dataSource

import com.example.yahoosportssoccer.data.model.Team
import retrofit2.Response

interface TeamRemoteDataSource {

    suspend fun getSoccerTeams(): Response<List<Team>>
}