package com.synrgy.wefly.data.api.json.register

data class RegisterRequest(
    val email: String,
    val password: String,
    val fullName: String,
    val phoneNumber: String,
    val dateOfBirth: String
)
