package com.example.yahoosportssoccer.data.model

import java.io.Serializable

data class TeamStandings(
    val teamId: String,
    val teamName: String,
    var wins: Int = 0,
    var losses: Int = 0,
    var draws: Int = 0,
    var totalGames: Int = 0,
    var winPercentage: Int = 0,
    val awayTeamName: String = "",
    val awayTeamId: String = ""
): Serializable
