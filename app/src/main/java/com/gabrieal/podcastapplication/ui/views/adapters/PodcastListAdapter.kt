package com.gabrieal.podcastapplication.ui.views.adapters

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabrieal.podcastapplication.R
import com.gabrieal.podcastapplication.data.models.podcast.PodcastItemModel
import com.gabrieal.podcastapplication.ui.views.activities.MainActivity
import java.util.Date

class PodcastListAdapter(
    private val mList: List<PodcastItemModel>?,
    private val mainActivity: MainActivity
) : RecyclerView
.Adapter<PodcastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_podcast_list_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val podcastListModel = mList?.get(position)

        holder.itemView.setOnClickListener {
            mainActivity.playAudio(podcastListModel)
        }

        holder.tvPodcastTitle.text = podcastListModel?.title

        setupType(holder, podcastListModel)
        setupGuestDetails(holder, podcastListModel)
        setupPublishedDate(holder, podcastListModel)
        setupPreviewImage(holder, podcastListModel)
    }

    private fun setupPreviewImage(
        holder: ViewHolder,
        podcastListModel: PodcastItemModel?
    ) {
        podcastListModel?.data?.image?.let {
            holder.ivPodcast.imageTintList = null
            Glide
                .with(holder.itemView.context)
                .load(it)
                .centerCrop()
                .into(holder.ivPodcast)
        }
    }

    private fun setupPublishedDate(
        holder: ViewHolder,
        podcastListModel: PodcastItemModel?
    ) {
        holder.tvPodcastPublishedDate.visibility = View.GONE

        podcastListModel?.data?.interviewtime?.let {
            holder.tvPodcastPublishedDate.visibility = View.VISIBLE
            val date = Date(it.toLong() * 1000)
            holder.tvPodcastPublishedDate.text = DateFormat.format("dd MMM yyyy, hh:mm a", date)
        }
    }

    private fun setupGuestDetails(
        holder: ViewHolder,
        podcastListModel: PodcastItemModel?
    ) {
        holder.tvPodcastSubtitle.visibility = View.GONE

        podcastListModel?.subtitle?.let {
            holder.tvPodcastSubtitle.visibility = View.VISIBLE
            holder.tvPodcastSubtitle.text = it
        }
    }

    private fun setupType(holder: ViewHolder, podcastListModel: PodcastItemModel?) {
        if (podcastListModel?.type == "livestream") {
            return
        }

        val totalSecs = podcastListModel?.data?.mp3_duration?.toInt()
        val minutes = (totalSecs?.rem(3600))?.div(60)
        val seconds = totalSecs?.rem(60)

        holder.tvPodcastType.text = String.format("%02d:%02d", minutes, seconds)
        holder.tvPodcastType.setBackgroundColor(
            ContextCompat.getColor(holder.itemView.context, R.color.black00)
        )
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvPodcastTitle: TextView = itemView.findViewById(R.id.tvPodcastTitle)
        val ivPodcast: ImageView = itemView.findViewById(R.id.ivPodcast)
        val tvPodcastType: TextView = itemView.findViewById(R.id.tvPodcastType)
        val tvPodcastSubtitle: TextView = itemView.findViewById(R.id.tvPodcastSubtitle)
        val tvPodcastPublishedDate: TextView = itemView.findViewById(R.id.tvPodcastPublishedDate)
    }
}