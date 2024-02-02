package com.synrgy.wefly.data.api.transaction

data class PassengerRequest(
    val firstName: String,
    val lastName: String,
    val nationality: String,
    val dateOfBirth: String
)
