package com.example.yahoosportssoccer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.yahoosportssoccer.R
import com.example.yahoosportssoccer.databinding.ActivityMainBinding
import com.example.yahoosportssoccer.presentation.viewmodel.TeamStandingsViewModel
import com.example.yahoosportssoccer.presentation.viewmodel.TeamStandingsViewModelFactory
import com.example.yahoosportssoccer.presentation.viewmodel.TeamViewModel
import com.example.yahoosportssoccer.presentation.viewmodel.TeamViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var factory: TeamViewModelFactory
    @Inject
    lateinit var standingsFactory: TeamStandingsViewModelFactory
    lateinit var viewModel: TeamViewModel
    lateinit var standingsViewModel: TeamStandingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)[TeamViewModel::class.java]
        standingsViewModel = ViewModelProvider(this, standingsFactory)[TeamStandingsViewModel::class.java]
        setSupportActionBar(binding.toolbar)
    }
}