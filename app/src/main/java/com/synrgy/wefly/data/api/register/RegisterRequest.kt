package com.synrgy.wefly.data.api.register

data class RegisterRequest(
    val username: String,
    val password: String,
    val fullName: String,
    val phoneNumber: String
)
