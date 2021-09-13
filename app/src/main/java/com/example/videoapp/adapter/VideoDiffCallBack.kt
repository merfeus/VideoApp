package com.example.videoapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.videoapp.model.Pics
import com.example.videoapp.model.VideoConfig

open class VideoDiffCallBack(
) : DiffUtil.ItemCallback<VideoConfig>() {
    override fun areItemsTheSame(oldItem: VideoConfig, newItem: VideoConfig): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: VideoConfig, newItem: VideoConfig): Boolean {
        return oldItem == newItem
    }

}