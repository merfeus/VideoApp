package com.example.videoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videoapp.R
import com.example.videoapp.databinding.ItemAdsBinding

class AdsAdapter : RecyclerView.Adapter<AdsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_ads, parent, false).let {
            return AdsViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 1
}

class AdsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding : ItemAdsBinding = ItemAdsBinding.bind(view)

}