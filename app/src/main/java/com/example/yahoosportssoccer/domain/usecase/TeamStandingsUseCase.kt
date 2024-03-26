package com.example.yahoosportssoccer.domain.usecase

import com.example.yahoosportssoccer.data.model.Team
import com.example.yahoosportssoccer.data.model.TeamStandings

class TeamStandingsUseCase {

    //Part of this calculation was done using help from AI tools
    fun execute(teams: List<Team>): List<TeamStandings> {
         val teamStandingsMap = mutableMapOf<String, TeamStandings>()

        for (team in teams) {
            if (!teamStandingsMap.containsKey(team.homeTeamId)) {
                teamStandingsMap[team.homeTeamId] = TeamStandings(team.homeTeamId, team.homeTeamName!!)
            }

            if (!teamStandingsMap.containsKey(team.awayTeamId)) {
                teamStandingsMap[team.awayTeamId] = TeamStandings(team.awayTeamId, team.awayTeamName!!)
            }

            val homeStanding = teamStandingsMap[team.homeTeamId]!!
            if (team.homeScore == team.awayScore) {
                homeStanding.draws++
            } else if (team.homeScore > team.awayScore) {
                homeStanding.wins++
            } else {
                homeStanding.losses++
            }
            homeStanding.totalGames++

            val awayStanding = teamStandingsMap[team.awayTeamId]!!
            if (team.awayScore == team.homeScore) {
                awayStanding.draws++
            } else if (team.awayScore > team.homeScore) {
                awayStanding.wins++
            } else {
                awayStanding.losses++
            }
            awayStanding.totalGames++
        }

        for (standing in teamStandingsMap.values) {
            val winPercentage = if (standing.totalGames > 0) {
                (standing.wins.toFloat() / standing.totalGames) * 100
            } else {
                0f
            }
            standing.winPercentage = winPercentage.toInt()
        }
        return teamStandingsMap.values.sortedByDescending { it.winPercentage }
    }

    fun executeSingleTeam(teams: List<Team>, teamId: String): List<TeamStandings> {
        val teamStandingsMap = mutableMapOf<String, TeamStandings>()
        val teamGames = teams.filter { it.homeTeamId == teamId || it.awayTeamId == teamId }

        for (game in teamGames) {

            if (!teamStandingsMap.containsKey(game.homeTeamId)) {
                teamStandingsMap[game.homeTeamId] = TeamStandings(game.homeTeamId, game.homeTeamName!!)
            }

            if (!teamStandingsMap.containsKey(game.awayTeamId)) {
                teamStandingsMap[game.awayTeamId] = TeamStandings(game.awayTeamId, game.awayTeamName!!)
            }

            val awayTeamStanding = teamStandingsMap[if (game.homeTeamId == teamId) game.awayTeamId else game.homeTeamId]!!
            if (game.homeScore == game.awayScore) {
                awayTeamStanding.draws++
            } else if (game.homeTeamId == teamId && game.homeScore > game.awayScore ||
                        game.awayTeamId == teamId && game.awayScore > game.homeScore) {
                awayTeamStanding.wins++
            } else {
                awayTeamStanding.losses++
            }
            awayTeamStanding.totalGames++
        }
        teamStandingsMap.remove(teamId)
        return teamStandingsMap.values.sortedByDescending { it.totalGames }
    }
}