package com.example.yahoosportssoccer.presentation.di

import android.app.Application
import com.example.yahoosportssoccer.domain.usecase.GetTeamStatsUseCase
import com.example.yahoosportssoccer.domain.usecase.TeamStandingsUseCase
import com.example.yahoosportssoccer.presentation.viewmodel.TeamStandingsViewModel
import com.example.yahoosportssoccer.presentation.viewmodel.TeamStandingsViewModelFactory
import com.example.yahoosportssoccer.presentation.viewmodel.TeamViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideTeamViewModelFactory(
        app: Application,
        getTeamStatsUseCase: GetTeamStatsUseCase
    ): TeamViewModelFactory {
        return TeamViewModelFactory(app, getTeamStatsUseCase)
    }

    @Singleton
    @Provides
    fun provideTeamStandingsViewModelFactory(
        getTeamStandingsUseCase: TeamStandingsUseCase
    ): TeamStandingsViewModelFactory {
        return TeamStandingsViewModelFactory(getTeamStandingsUseCase)
    }
}