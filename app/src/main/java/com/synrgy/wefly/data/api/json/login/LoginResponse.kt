package com.synrgy.wefly.data.api.json.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("refresh_token")
    val refreshToken: String?,
    val code: Int?,
    val scope: String?,
    @SerializedName("token_type")
    val tokenType: String?,
    val message: String?,
    @SerializedName("expires_in")
    val exp: Int?,
    val jti: String?,
    val error: String?
)
