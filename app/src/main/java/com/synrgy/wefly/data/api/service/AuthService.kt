package com.synrgy.wefly.data.api.service

import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.ContentResponse
import com.synrgy.wefly.data.api.json.airport.list.AirportListResponse
import com.synrgy.wefly.data.api.json.flight.FlightContent
import com.synrgy.wefly.data.api.json.forgetpassword.ForgotPassRequest
import com.synrgy.wefly.data.api.json.forgetpassword.ForgotPassResponse
import com.synrgy.wefly.data.api.json.forgetpassword.changepassword.ChangePasswordRequest
import com.synrgy.wefly.data.api.json.login.LoginRequest
import com.synrgy.wefly.data.api.json.login.LoginResponse
import com.synrgy.wefly.data.api.json.register.AccountRequest
import com.synrgy.wefly.data.api.json.register.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthService {
    @POST("user-login/login")
    fun login(
        @Body loginRequest: LoginRequest,
        @Header("Authorization") header: String? = ""
    ): Call<LoginResponse>

    @POST("user-register/register-user")
    fun register(@Body accountRequest: AccountRequest, @Header("Authorization") header: String? = ""): Call<RegisterResponse>

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

    @POST("forget-password/forgot-password")
    fun forgotPassword(@Body forgotPasswordRequest: ForgotPassRequest): Call<ForgotPassResponse>

    @POST("forget-password/check-token/{token}")
    fun otpPassword(
        @Path("token") token: String
    ): Call<ForgotPassResponse>

    @PUT("forget-password/change-password")
    fun changePassword(@Body passBody: ChangePasswordRequest): Call<ForgotPassResponse>
}