package com.example.yahoosportssoccer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yahoosportssoccer.R
import com.example.yahoosportssoccer.data.util.Resource
import com.example.yahoosportssoccer.databinding.FragmentTeamsStatsBinding
import com.example.yahoosportssoccer.presentation.adapter.TeamsAdapter
import com.example.yahoosportssoccer.presentation.viewmodel.TeamStandingsViewModel
import com.example.yahoosportssoccer.presentation.viewmodel.TeamViewModel

class TeamsStatsFragment : Fragment() {
    private lateinit var teamViewModel: TeamViewModel
    private lateinit var standingsViewModel: TeamStandingsViewModel
    private lateinit var teamsAdapter: TeamsAdapter
    private lateinit var fragmentTeamsStatsBinding: FragmentTeamsStatsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teams_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentTeamsStatsBinding = FragmentTeamsStatsBinding.bind(view)
        teamViewModel = (activity as MainActivity).viewModel
        standingsViewModel = (activity as MainActivity).standingsViewModel
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.title_all_teams)

        teamsAdapter = TeamsAdapter()
        viewTeamsList()
        initRecyclerView()

        teamsAdapter.setOnItemClickListener {
            findNavController().navigate(
                TeamsStatsFragmentDirections.actionTeamsStatsFragmentToTeamDetailsFragment(
                    it.teamId,
                    it.teamName
                )
            )
        }
    }

    private fun viewTeamsList() {
        teamViewModel.getTeamStats()
        teamViewModel.teamStats.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hidProgressBar()
                    response.data?.let {
                        val teams = standingsViewModel.calculateTeamStandings(it.toList())
                        teamsAdapter.differ.submitList(teams)
                    }
                }
                is Resource.Error -> {
                    hidProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG).show()
                    }

                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun initRecyclerView() {
        fragmentTeamsStatsBinding.rvTeams.apply {
            adapter = teamsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar() {
        fragmentTeamsStatsBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hidProgressBar() {
        fragmentTeamsStatsBinding.progressBar.visibility = View.INVISIBLE
    }
}
