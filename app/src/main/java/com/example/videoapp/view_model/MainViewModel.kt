package com.example.videoapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoapp.model.Pics
import com.example.videoapp.model.VideoConfig
import com.example.videoapp.repository.PicsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PicsRepository) : ViewModel() {

    private val _IMG = MutableLiveData<List<Pics>>()
    val pics: LiveData<List<Pics>> = _IMG

    private val _VIDEOS = MutableLiveData<List<VideoConfig>>()
    val videos: LiveData<List<VideoConfig>> = _VIDEOS

    fun getAllPics(query: String = "") {
        viewModelScope.launch {
            repository.getAllPicsService(query = query).let {
                _IMG.value = it
            }

        }
    }

    fun getVideo(query: String = ""){
        viewModelScope.launch {
            repository.getALlVideoService(query = query).let {
                _VIDEOS.value = it
            }
        }
    }

}