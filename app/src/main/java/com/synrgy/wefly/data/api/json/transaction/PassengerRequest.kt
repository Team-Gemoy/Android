package com.synrgy.wefly.data.api.json.transaction

data class PassengerRequest(
    val firstName: String,
    val lastName: String,
    val nationality: String,
    val dateOfBirth: String
)
