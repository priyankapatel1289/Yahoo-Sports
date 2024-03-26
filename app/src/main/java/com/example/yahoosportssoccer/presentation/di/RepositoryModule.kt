package com.example.yahoosportssoccer.presentation.di

import com.example.yahoosportssoccer.data.repository.dataSourceImpl.TeamRepositoryImpl
import com.example.yahoosportssoccer.data.repository.dataSource.TeamRemoteDataSource
import com.example.yahoosportssoccer.domain.repository.TeamRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSoccerTeamsRepository(
        teamRemoteDataSource: TeamRemoteDataSource
    ): TeamRepository {
        return TeamRepositoryImpl(teamRemoteDataSource)
    }
}