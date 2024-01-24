package com.synrgy.wefly.data.api.airport.save

data class AirportSaveResponse(
    val code: Int,
    val data: AirportSaveData,
    val message: String
)

data class AirportSaveData(
    val createdDate: String,
    val deletedDate: String?,
    val updatedDate: String?,
    val id: Int,
    val name: String,
    val airportCode: String,
    val city: String,
    val country: String,
    val status: Boolean
)
