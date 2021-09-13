package com.example.videoapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videoapp.R
import com.example.videoapp.databinding.ItemVideoBinding
import com.example.videoapp.model.VideoConfig

const val NORMAL_VIEW = 0
const val ADS_VIEW = 1

class AdapterVideo : ListAdapter<VideoConfig, RecyclerView.ViewHolder>(VideoDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ADS_VIEW) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_ads, parent, false).let {
                return AdsViewHolder(it)
            }
        }

        LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false).let {
            return VideoViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == NORMAL_VIEW) {
            holder as VideoViewHolder
            getItem(position).let {
                holder.bind(it)
            }
        } else {
            holder as AdsViewHolder
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 4 == 0 && position != 0 ) {
            ADS_VIEW
        } else {
            NORMAL_VIEW
        }
    }

}

class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: ItemVideoBinding = ItemVideoBinding.bind(view)

    fun bind(video: VideoConfig) {

        binding.textViewName.text = video.user
        binding.textViewLikes.text = video.likes.toString()

        Glide.with(itemView)
            .load(video.userImageURL)
            .into(binding.imageViewAvatar)

        binding.videoView.setVideoURI(Uri.parse(video.videos.medium.url))
        binding.videoView.start()
        binding.bottomBarInfo.visibility = View.GONE
        binding.videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            val videoRatio = mediaPlayer.videoWidth / mediaPlayer.videoHeight.toFloat()
            val screenRatio = binding.videoView.width / binding.videoView.height.toFloat()
            val scaleX = videoRatio / screenRatio
            if (scaleX >= 1f) {
                binding.videoView.scaleX = scaleX
            } else {
                binding.videoView.scaleY = 1f / scaleX
            }
            binding.bottomBarInfo.visibility = View.VISIBLE
        }

    }

}


