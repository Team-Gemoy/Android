package com.synrgy.wefly.data.api.transaction

import com.synrgy.wefly.data.api.flight.FlightCard

data class TransactionListResponse (
    val createdDate: String?,
    val deletedDate: String?,
    val updatedDate: String?,
    val id: Int,
    val passengers: ArrayList<Passenger>,
    val transactionDetails: ArrayList<TransactionDetailsListResponse>,
    val orderer: Orderer,
    val adultPassenger: Int,
    val childPassenger: Int,
    val infantPassenger: Int,
    val totalPrice: Int,
    val status: String,
    val paymentProof: String?
)

data class TransactionDetailsListResponse(
    val id: Int,
    val flight: FlightCard,
    val totalPriceAdult: Int,
    val totalPriceChild: Int,
    val totalPriceInfant: Int
)

data class Orderer(
    val createdDate: String?,
    val deletedDate: String?,
    val updatedDate: String?,
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
)