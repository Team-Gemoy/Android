package com.synrgy.wefly.data.api.json.flight

import com.synrgy.wefly.data.api.json.airport.list.ContentAirport
import com.synrgy.wefly.data.api.json.airport.list.PageAble

data class FlightListResponse(
    val content: ArrayList<FlightContent>,
    val pageable: PageAble,
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
    val flightNumber: String?,
    val departureAirport: ContentAirport?,
    val arrivalAirport: ContentAirport?,
    val airplane: Airplane?,
    val departureDate: String?,
    val arrivalDate: String?,
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