package com.synrgy.wefly.data.api.forgetpassword

data class ForgotPassResponse(
    val code: Int,
    val data: String?,
    val message: String?,
    val error: String?
)
