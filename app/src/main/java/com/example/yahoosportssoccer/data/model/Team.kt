package com.example.yahoosportssoccer.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "teams")
data class Team(

    @PrimaryKey
    @SerializedName("homeTeamId")
    val homeTeamId: String,
    @SerializedName("homeScore")
    val homeScore: Int = 0,
    @SerializedName("homeTeamName")
    val homeTeamName: String?,
    @SerializedName("gameId")
    val gameId: String,
    @SerializedName("awayScore")
    val awayScore: Int = 0,
    @SerializedName("awayTeamId")
    val awayTeamId: String,
    @SerializedName("awayTeamName")
    val awayTeamName: String?
): Serializable