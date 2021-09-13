package com.example.videoapp.repository

import com.example.videoapp.model.Pics
import com.example.videoapp.model.Video
import com.example.videoapp.model.VideoConfig
import com.example.videoapp.service.ServicePic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PicsRepository @Inject constructor(private val servicePic: ServicePic) {

    suspend fun getAllPicsService(query: String): List<Pics>? {
        return withContext(Dispatchers.Default) {
            val response = servicePic.getImage(q = query)
            val processesResponse = processData(response)
            processesResponse?.hits
        }
    }

    suspend fun getALlVideoService(query: String): List<VideoConfig>?{
        return withContext(Dispatchers.Default){
            val response = servicePic.getVideo(q = query)
            val processResponse = processData(response)
            processResponse?.hits
        }
    }


     private fun <T> processData(response: Response<T>): T?{
         return if (response.isSuccessful) response.body() else null
     }
}