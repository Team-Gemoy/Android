package com.synrgy.wefly.data.api.json.register

data class AccountRequest(
    val email: String,
    val password: String? = "",
    val fullName: String,
    val phoneNumber: String,
    val dateOfBirth: String,
    val gender: String? = "",
    val city: String? = "",
)
