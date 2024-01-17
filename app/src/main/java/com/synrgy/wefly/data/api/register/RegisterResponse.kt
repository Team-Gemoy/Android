package com.synrgy.wefly.data.api.register

data class RegisterResponse (
    val code: Int,
    val data: String?,
    val message: String?,
    val error: String?
)