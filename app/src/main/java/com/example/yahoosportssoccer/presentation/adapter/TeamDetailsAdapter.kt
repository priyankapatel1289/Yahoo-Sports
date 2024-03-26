package com.example.yahoosportssoccer.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.yahoosportssoccer.R
import com.example.yahoosportssoccer.data.model.TeamStandings
import com.example.yahoosportssoccer.databinding.TeamListItemBinding

class TeamDetailsAdapter: RecyclerView.Adapter<TeamDetailsAdapter.TeamDetailsViewHolder>() {
    private val games: MutableList<TeamStandings> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamDetailsViewHolder {
        val binding = TeamListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: TeamDetailsViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.evenColor))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.oddColor))
        }
    }

    fun updateGamesList(gamesList: List<TeamStandings>) {
        games.clear()
        games.addAll(gamesList)
        notifyDataSetChanged()
    }

    inner class TeamDetailsViewHolder(
        private val binding: TeamListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(teamStandings: TeamStandings) {
            binding.apply {
                tvTeamName.text = teamStandings.teamName
                tvWinScore.text = teamStandings.wins.toString()
                tvDrawScore.text = teamStandings.draws.toString()
                tvLossScore.text = teamStandings.losses.toString()
                tvWinPercentage.text = teamStandings.totalGames.toString()
            }
        }
    }

}