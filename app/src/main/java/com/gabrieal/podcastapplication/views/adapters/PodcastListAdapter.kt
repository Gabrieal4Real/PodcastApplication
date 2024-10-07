package com.gabrieal.podcastapplication.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabrieal.podcastapplication.R

class PodcastListAdapter(private val mList: ArrayList<Any>?) : RecyclerView
    .Adapter<PodcastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bfm_list_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val tvHighestTitle: TextView = itemView.findViewById(R.id.tvHighestTitle)
        val tvHighestDesc: TextView = itemView.findViewById(R.id.tvHighestDesc)
        val tvLowestTitle: TextView = itemView.findViewById(R.id.tvLowestTitle)
        val tvLowestDesc: TextView = itemView.findViewById(R.id.tvLowestDesc)
    }
}