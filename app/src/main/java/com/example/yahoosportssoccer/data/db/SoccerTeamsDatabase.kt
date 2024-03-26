package com.example.yahoosportssoccer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yahoosportssoccer.data.model.Team

@Database(entities = [Team::class],
            version = 1,
            exportSchema = false)
abstract class SoccerTeamsDatabase: RoomDatabase() {
    abstract fun teamDao(): TeamDao
}