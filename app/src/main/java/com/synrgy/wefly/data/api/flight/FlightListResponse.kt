package com.synrgy.wefly.data.api.flight

import com.synrgy.wefly.data.api.airport.list.ContentAirport
import com.synrgy.wefly.data.api.airport.list.PageAble

data class FlightListResponse(
    val code: Int,
    val data: FlightData
)

data class FlightData(
    val content: ArrayList<FlightContent>,
    val pageable: PageAble,
    val message: String
)

data class FlightContent(
    val id: Int,
    val seatClass: String,
    val basePriceAdult: Int,
    val basePriceChild: Int,
    val basePriceInfant: Int,
    val availableSeat: Int,
    val flight: FlightCard
)

data class FlightCard(
    val createdDate: String?,
    val deletedDate: String?,
    val updatedDate: String?,
    val id: Int,
    val departureAirport: ContentAirport,
    val arrivalAirport: ContentAirport,
    val airplane: Airplane,
    val departureDate: String,
    val arrivalDate: String,
    val departureTime: String?,
    val arrivalTime: String?,
    val basePrice: Int
)

data class Airplane(
    val createdDate: String?,
    val deletedDate: String?,
    val updatedDate: String?,
    val id: Int,
    val name: String,
    val type: String,
    val airline: Airline,
    val seats: ArrayList<Seats>
)

data class Airline(
    val createdDate: String?,
    val deletedDate: String?,
    val updatedDate: String?,
    val id: Int,
    val name: String,
    val discountChild: Int,
    val discountInfant: Int,
    val businessMultiplier: Int
)

data class Seats(
    val createdDate: String?,
    val deletedDate: String?,
    val updatedDate: String?,
    val id: Int,
    val seatClass: String,
    val seatRow: Int,
    val seatColumn: Int
)