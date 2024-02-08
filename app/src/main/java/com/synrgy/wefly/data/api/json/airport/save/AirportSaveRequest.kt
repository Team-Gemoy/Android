package com.synrgy.wefly.data.api.json.airport.save

data class AirportSaveRequest(
    val name: String,
    val airportCode: String,
    val city: String,
    val country: String,
    val status: String
)
