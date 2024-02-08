package com.synrgy.wefly.data.api.service

import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.ContentResponse
import com.synrgy.wefly.data.api.json.list.AirportListResponse
import com.synrgy.wefly.data.api.json.flight.FlightContent
import com.synrgy.wefly.data.api.json.login.LoginRequest
import com.synrgy.wefly.data.api.json.login.LoginResponse
import com.synrgy.wefly.data.api.json.register.RegisterRequest
import com.synrgy.wefly.data.api.json.register.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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

    @GET("flight/list")
    suspend fun getFlight(
        //  @Path("departDate") departDate: String
        @Query("departureAirportId") departureAirportId: Int,
        @Query("arrivalAirportId") arrivalAirportId: Int,
        @Query("departDate") departDate: String,
        @Query("seatClass") seatClass: String,
        @Query("numberOfPassenger") numberOfPassenger: Int
    ): HeaderResponse<ContentResponse<FlightContent>>
}