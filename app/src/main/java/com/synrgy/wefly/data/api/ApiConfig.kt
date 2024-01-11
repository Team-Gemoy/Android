package com.synrgy.wefly.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    fun getApiService(): ApiService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS) // Set the connection timeout
            .readTimeout(20, TimeUnit.SECONDS) // Set the read timeout
            .writeTimeout(20, TimeUnit.SECONDS) // Set the write timeout
            .addInterceptor(loggingInterceptor)
         //   .addInterceptor(bearerTokenInterceptor) // Add the custom interceptor
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://188.166.196.8:8081/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}