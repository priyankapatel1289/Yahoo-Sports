package com.example.yahoosportssoccer.domain.repository

import com.example.yahoosportssoccer.data.model.Team
import com.example.yahoosportssoccer.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface TeamRepository {

    suspend fun getTeamsStats(): Resource<List<Team>>
}