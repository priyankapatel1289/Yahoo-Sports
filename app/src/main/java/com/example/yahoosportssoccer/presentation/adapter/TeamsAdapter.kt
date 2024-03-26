package com.example.yahoosportssoccer.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yahoosportssoccer.R
import com.example.yahoosportssoccer.data.model.Team
import com.example.yahoosportssoccer.data.model.TeamStandings
import com.example.yahoosportssoccer.databinding.TeamListItemBinding

class TeamsAdapter: RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<TeamStandings>() {
        override fun areItemsTheSame(oldItem: TeamStandings, newItem: TeamStandings): Boolean {
            return oldItem.teamId == newItem.teamId
        }

        override fun areContentsTheSame(oldItem: TeamStandings, newItem: TeamStandings): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val binding = TeamListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        val team = differ.currentList[position]
        holder.bind(team)

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.evenColor))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.oddColor))
        }
    }

    private var onItemClickListener: ((TeamStandings)->Unit)?=null

    fun setOnItemClickListener(listner: (TeamStandings)->Unit) {
        onItemClickListener = listner
    }

    inner class TeamsViewHolder(
        val binding: TeamListItemBinding
        ): RecyclerView.ViewHolder(binding.root) {
            fun bind(team: TeamStandings) {
                binding.apply {
                    tvTeamName.text = team.teamName
                    tvWinScore.text = team.wins.toString()
                    tvDrawScore.text = team.draws.toString()
                    tvLossScore.text = team.losses.toString()
                    tvWinPercentage.text = "${team.winPercentage}%"
                    root.setOnClickListener {
                        onItemClickListener?.let {
                            it(team)
                        }
                    }
                }
            }
    }
}