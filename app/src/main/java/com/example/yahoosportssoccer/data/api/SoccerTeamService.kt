package com.example.yahoosportssoccer.data.api

import com.example.yahoosportssoccer.data.model.Team
import retrofit2.Response
import retrofit2.http.GET

interface SoccerTeamService {

    @GET("soccer_game_results.json")
    suspend fun getSoccerTeams(): Response<List<Team>>
}