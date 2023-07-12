package com.example.CodeFriend.network

import com.example.CodeFriend.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    companion object{
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api: NotificationApi by lazy {
            retrofit.create(NotificationApi::class.java)
        }
    }
}