package com.example.videoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videoapp.R
import com.example.videoapp.databinding.ItemImageBinding
import com.example.videoapp.model.Pics

class AdapterImage : ListAdapter<Pics, RecyclerView.ViewHolder>(ImageDiiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ADS_VIEW) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_ads, parent, false).let {
                return AdsViewHolder(it)
            }
        }

        LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false).let {
            return ImageViewHolder(it)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == NORMAL_VIEW) {
            holder as ImageViewHolder
            getItem(position).let {
                holder.bind(it)
            }
        } else {
            holder as AdsViewHolder
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 2) {
            ADS_VIEW
        } else {
            NORMAL_VIEW
        }
    }
}


class ImageViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    private val binding: ItemImageBinding = ItemImageBinding.bind(item)

    fun bind(pics: Pics) {

        binding.textViewName.text = pics.user

        Glide.with(itemView.context)
            .load(pics.largeImageURL)
            .into(binding.imageViewPhoto)

        Glide.with(itemView.context)
            .load(pics.userImageURL)
            .into(binding.imageViewAvatar)
    }

}
