package com.synrgy.wefly.data.api

import com.synrgy.wefly.data.api.login.LoginRequest
import com.synrgy.wefly.data.api.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("user-login/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}