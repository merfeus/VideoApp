package com.example.videoapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.videoapp.model.Pics

open class ImageDiiffCallBack(
) : DiffUtil.ItemCallback<Pics>() {
    override fun areItemsTheSame(oldItem: Pics, newItem: Pics): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pics, newItem: Pics): Boolean {
        return oldItem == newItem
    }
}