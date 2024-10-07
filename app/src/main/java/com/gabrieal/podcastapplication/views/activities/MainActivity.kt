package com.gabrieal.podcastapplication.views.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrieal.podcastapplication.R
import com.gabrieal.podcastapplication.databinding.ActivityMainBinding
import com.gabrieal.podcastapplication.viewmodel.podcast.PodcastViewModelImpl
import com.gabrieal.podcastapplication.views.adapters.PodcastListAdapter
import com.gabrieal.podcastapplication.views.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private var binding: ActivityMainBinding? = null
    private val podcastViewModel: PodcastViewModelImpl by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSetupViewModel()
        onBindData()
        setupUI()
        observeResponses()
    }

    private fun onSetupViewModel() {
        podcastViewModel.getPodcastList()
    }

    private fun onBindData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.lifecycleOwner = this
        binding?.podcastViewModel = podcastViewModel
    }

    private fun setupUI() {
        binding?.rvPodcastList?.layoutManager = LinearLayoutManager(this)
        val adapter = PodcastListAdapter(null)
        binding?.rvPodcastList?.adapter = adapter
    }

    private fun observeResponses() {

    }
}