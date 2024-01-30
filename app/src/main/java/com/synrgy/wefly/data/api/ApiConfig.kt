package com.synrgy.wefly.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    private val bearerTokenInterceptor = Interceptor { chain ->
        val original = chain.request()

        // Retrieve the token from the repository
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyLXJlc291cmNlIl0sImZ1bGxfbmFtZSI6Im1hcnRpbiIsInVzZXJfbmFtZSI6ImxhZXR1emdAZ21haWwuY29tIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImlkIjo2LCJleHAiOjE3MDY2MTI0NzMsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiJySTZ3ZURfTTZpX1YzQll5OFdrUkF6cjRMcDgiLCJjbGllbnRfaWQiOiJteS1jbGllbnQtd2ViIn0.PTRwL5p-mQ3kGX_lgDNP1WHGf_XSpd0dVRy1PDFilNs" //PreferenceRepositoryImpl().getToken()

        // Add the token to the request headers
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer $token")

        val request = requestBuilder.build()
        chain.proceed(request)
    }

    fun getApiService(): ApiService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS) // Set the connection timeout
            .readTimeout(20, TimeUnit.SECONDS) // Set the read timeout
            .writeTimeout(20, TimeUnit.SECONDS) // Set the write timeout
            .addInterceptor(loggingInterceptor)
            .addInterceptor(bearerTokenInterceptor) // Add the custom interceptor
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://188.166.196.8:8081/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}