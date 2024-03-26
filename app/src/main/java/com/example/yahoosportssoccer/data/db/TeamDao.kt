package com.example.yahoosportssoccer.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yahoosportssoccer.data.model.Team

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTeams(teams: List<Team>)

    @Query("SELECT * FROM teams")
    suspend fun getTeams(): List<Team>
}