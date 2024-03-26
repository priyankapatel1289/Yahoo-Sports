package com.example.yahoosportssoccer.presentation.di

import com.example.yahoosportssoccer.domain.repository.TeamRepository
import com.example.yahoosportssoccer.domain.usecase.GetTeamStatsUseCase
import com.example.yahoosportssoccer.domain.usecase.TeamStandingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetTeamStatsUseCase(
        teamRepository: TeamRepository
    ) : GetTeamStatsUseCase {
        return GetTeamStatsUseCase(teamRepository)
    }

    @Singleton
    @Provides
    fun provideGetTeamStandingsUseCase() : TeamStandingsUseCase {
        return TeamStandingsUseCase()
    }
}