package com.example.videoapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videoapp.R
import com.example.videoapp.adapter.AdapterImage
import com.example.videoapp.adapter.AdapterVideo
import com.example.videoapp.adapter.HeaderAdapter
import com.example.videoapp.databinding.MainFragmentBinding
import com.example.videoapp.model.Pics
import com.example.videoapp.model.VideoConfig
import com.example.videoapp.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment(private val itemTypeApp: ItemTypeApp) : Fragment(R.layout.main_fragment) {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    lateinit var adapters: ConcatAdapter
    private val adapterImage = AdapterImage()
    private val adapterVideo = AdapterVideo()
    private val adapterHeader = HeaderAdapter {
        if (itemTypeApp == ItemTypeApp.VIDEO) viewModel.getVideo(it) else viewModel.getAllPics(it)
    }
    private val observerImages = Observer<List<Pics>> {
        adapterImage.submitList(it)
    }
    private val observerVideo = Observer<List<VideoConfig>> {
        adapterVideo.submitList(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = MainFragmentBinding.bind(view)
        adapters =
            if (itemTypeApp == ItemTypeApp.VIDEO) ConcatAdapter(adapterHeader, adapterVideo) else ConcatAdapter(
                adapterHeader,
                adapterImage
            )
        startObservers()
        startRecyclerView()
    }

    private fun startObservers() {
        viewModel.pics.observe(viewLifecycleOwner, observerImages)
        viewModel.videos.observe(viewLifecycleOwner, observerVideo)
    }


    private fun startRecyclerView() = binding.recyclerVIewSreen.apply {
        adapter = adapters
        layoutManager = LinearLayoutManager(requireContext())
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                hideSoftInput()

            }
        })
        if (itemTypeApp == ItemTypeApp.VIDEO) viewModel.getVideo() else viewModel.getAllPics()
    }

    fun View.hideSoftInput() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

}

enum class ItemTypeApp {
    VIDEO,
    IMAGE
}