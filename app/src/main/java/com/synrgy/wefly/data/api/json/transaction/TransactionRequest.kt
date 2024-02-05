package com.synrgy.wefly.data.api.json.transaction

data class TransactionRequest(
    val adultPassenger: Int?,
    val childPassenger: Int?,
    val infantPassenger: Int?,
    val passengers: ArrayList<Passenger>,
    val orderer: Orderer,
    val transactionDetails: ArrayList<TransactionDetailRequest>
)

data class TransactionDetailRequest(
    val flightClassId: Int
)