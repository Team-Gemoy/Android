package com.synrgy.wefly.data.api.service

import com.synrgy.wefly.data.api.json.airport.list.AirportListResponse
import com.synrgy.wefly.data.api.json.login.LoginRequest
import com.synrgy.wefly.data.api.json.login.LoginResponse
import com.synrgy.wefly.data.api.json.register.RegisterRequest
import com.synrgy.wefly.data.api.json.register.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("user-login/login")
    fun login(
        @Body loginRequest: LoginRequest,
        @Header("Authorization") header: String? = ""
    ): Call<LoginResponse>

    @POST("user-register/register-user")
    fun register(@Body registerRequest: RegisterRequest, @Header("Authorization") header: String? = ""): Call<RegisterResponse>

    //Airport
    @GET("airport/list")
    suspend fun getAirportList(): AirportListResponse
}