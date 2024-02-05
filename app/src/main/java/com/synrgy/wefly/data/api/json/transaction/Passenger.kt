package com.synrgy.wefly.data.api.json.transaction

data class Passenger(
    val id: Int,
    var firstName: String,
    var lastName: String,
    var nationality: String,
    var dateOfBirth: String
)
