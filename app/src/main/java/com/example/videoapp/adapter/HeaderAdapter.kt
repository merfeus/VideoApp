package com.example.videoapp.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videoapp.R
import com.example.videoapp.databinding.HeaderItemBinding

class HeaderAdapter(val onTap: (String) -> Unit) : RecyclerView.Adapter<HeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.header_item, parent, false).apply {
            return HeaderViewHolder(this, onTap)
        }
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind()
    }
    override fun getItemCount(): Int = 1
}

class HeaderViewHolder(item: View, val onTap: (String) -> Unit) : RecyclerView.ViewHolder(item) {

    private val binding: HeaderItemBinding = HeaderItemBinding.bind(itemView)

    fun bind() {
        binding.ediTextSearchOnHeader.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    if (it.length > 3) {onTap(it.toString())
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

}