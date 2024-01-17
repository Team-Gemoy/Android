package com.synrgy.wefly.data.api

import com.synrgy.wefly.data.api.forgetpassword.ForgotPassRequest
import com.synrgy.wefly.data.api.forgetpassword.ForgotPassResponse
import com.synrgy.wefly.data.api.login.LoginRequest
import com.synrgy.wefly.data.api.login.LoginResponse
import com.synrgy.wefly.data.api.register.RegisterRequest
import com.synrgy.wefly.data.api.register.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("user-login/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("user-register/register-user")
    fun register(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("forget-password/forgot-password")
    fun forgotPassword(@Body forgotPasswordRequest: ForgotPassRequest): Call<ForgotPassResponse>
}