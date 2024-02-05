package com.synrgy.wefly.data.api.json.register

data class RegisterResponse (
    val code: Int,
    val data: String?,
    val message: String?,
    val error: String?
)