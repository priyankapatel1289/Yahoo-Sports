package com.example.yahoosportssoccer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yahoosportssoccer.R
import com.example.yahoosportssoccer.data.util.Resource
import com.example.yahoosportssoccer.databinding.FragmentTeamDetailsBinding
import com.example.yahoosportssoccer.presentation.adapter.TeamDetailsAdapter
import com.example.yahoosportssoccer.presentation.viewmodel.TeamStandingsViewModel
import com.example.yahoosportssoccer.presentation.viewmodel.TeamViewModel

class TeamDetailsFragment : Fragment() {
    private lateinit var standingsViewModel: TeamStandingsViewModel
    private lateinit var teamDetailsAdapter: TeamDetailsAdapter
    private lateinit var teamViewModel: TeamViewModel
    private lateinit var fragmentTeamDetailsBinding: FragmentTeamDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentTeamDetailsBinding = FragmentTeamDetailsBinding.bind(view)
        teamViewModel = (activity as MainActivity).viewModel
        standingsViewModel = (activity as MainActivity).standingsViewModel
        teamDetailsAdapter = TeamDetailsAdapter()
        val args: TeamDetailsFragmentArgs by navArgs()
        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.teamName
        viewTeamsList(args.teamId)
        initRecyclerView()
    }

    private fun viewTeamsList(teamId: String) {
            teamViewModel.teamStats.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            val teamStats = standingsViewModel.calculateIndividualTeamStandings(it.toList(), teamId)
                            teamDetailsAdapter.updateGamesList(teamStats)
                        }
                    }
                    is Resource.Error -> {
                        response.message?.let {
                            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                    is Resource.Loading -> {
                        Toast.makeText(activity, "Loading", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun initRecyclerView() {
        fragmentTeamDetailsBinding.rvTeamDetails.apply {
            adapter = teamDetailsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}