package com.example.videoapp.di

import android.content.Context
import com.example.videoapp.service.ServicePic
import com.example.videoapp.service.notification.NotificationHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providePixabayApi(retrofit: Retrofit): ServicePic =
        retrofit.create(ServicePic::class.java)

    @Provides
    fun provideNotificationHandler(@ApplicationContext context: Context): NotificationHandler =
        NotificationHandler(context)
}