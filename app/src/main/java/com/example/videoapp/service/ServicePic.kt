package com.example.videoapp.service

import com.example.videoapp.BuildConfig
import com.example.videoapp.model.PicsModel
import com.example.videoapp.model.VideoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServicePic {

    @GET("/api/")
    suspend fun getImage(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") q: String,
        @Query("lang") lang: String = "pt"
    ): Response<PicsModel>

    @GET("/api/videos")
    suspend fun getVideo(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") q: String,
        @Query("lang") lang: String = "pt"
    ): Response<VideoModel>
}