package com.gabrieal.podcastapplication.ui.views.activities

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrieal.podcastapplication.R
import com.gabrieal.podcastapplication.databinding.ActivityMainBinding
import com.gabrieal.podcastapplication.data.models.podcast.PodcastItemModel
import com.gabrieal.podcastapplication.ui.viewmodel.podcast.PodcastViewModelImpl
import com.gabrieal.podcastapplication.ui.views.adapters.PodcastListAdapter
import com.gabrieal.podcastapplication.ui.views.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException


class MainActivity : BaseActivity() {
    private var binding: ActivityMainBinding? = null
    private val podcastViewModel: PodcastViewModelImpl by viewModel()

    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private var currentlyPlaying: String? = null

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
    }

    private fun setupUI() {
        binding?.rvPodcastList?.layoutManager = LinearLayoutManager(this)

        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )

        binding?.ivPodcastPlayPause?.setOnClickListener {
            if (mediaPlayer.isPlaying)
                clickOnPause()
            else
                clickOnStart()
        }
    }

    private fun observeResponses() {
        podcastViewModel.observePodcastList().observe(this, {
            val adapter = PodcastListAdapter(it?.toList(), this)
            binding?.rvPodcastList?.adapter = adapter
        })
    }

    fun playAudio(podcastListModel: PodcastItemModel?) {
        val tempCurrentlyPlaying = if (podcastListModel?.type == "livestream")
            podcastListModel.source
        else
            podcastListModel?.data?.mp3

        if (currentlyPlaying == tempCurrentlyPlaying) {
            if (mediaPlayer.isPlaying)
                clickOnPause()
            else
                clickOnStart()
            return
        }

        binding?.llBottomPlayer?.visibility = View.VISIBLE
        binding?.tvPodcastMainTitle?.isSelected = true
        binding?.tvPodcastMainTitle?.text = podcastListModel?.title

        podcastListModel?.subtitle?.let {
            binding?.tvPodcastMainTitle?.text = "${podcastListModel?.title} • $it"
        }

        currentlyPlaying = tempCurrentlyPlaying

        try {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(currentlyPlaying)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun clickOnStart() {
        binding?.ivPodcastPlayPause?.setImageResource(R.drawable.pause)
        mediaPlayer.start()
    }

    private fun clickOnPause() {
        binding?.ivPodcastPlayPause?.setImageResource(R.drawable.play)
        mediaPlayer.pause()

    }
}