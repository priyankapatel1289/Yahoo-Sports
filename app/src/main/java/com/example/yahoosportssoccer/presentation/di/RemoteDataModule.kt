package com.example.yahoosportssoccer.presentation.di

import com.example.yahoosportssoccer.data.api.SoccerTeamService
import com.example.yahoosportssoccer.data.repository.dataSource.TeamRemoteDataSource
import com.example.yahoosportssoccer.data.repository.dataSourceImpl.TeamRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideTeamStatsRemoteDataSource(
        soccerTeamService: SoccerTeamService
    ): TeamRemoteDataSource {
        return TeamRemoteDataSourceImpl(soccerTeamService)
    }
}