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

class AdapterImage : ListAdapter<Pics, ImageViewHolder>(ImageDiiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false).let {
                return ImageViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        getItem(position).let { image ->
            holder.bind(image)
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