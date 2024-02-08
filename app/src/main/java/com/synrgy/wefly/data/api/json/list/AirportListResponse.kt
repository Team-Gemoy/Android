package com.synrgy.wefly.data.api.json.list

import com.google.gson.annotations.SerializedName

data class AirportListResponse (
    val data: DataAirport
)

data class DataAirport (
    val content: ArrayList<ContentAirport>,
    val pageable: PageAble,
    val totalPages: Int,
    val last: Boolean,
    val totalElements: Int,
    val size: Int,
    val number: Int,
    val sort: PageAbleSort,
    val numberIfElements: Int,
    val first: Boolean,
    val empty: Boolean
)

data class ContentAirport(
    val createdDate: String?,
    val deletedDate: String?,
    val updatedDate: String?,
    val id: Int,
    val name: String,
    val airportCode: String,
    val city: String,
    val country: String,
    val status: Boolean
)

data class PageAble(
    val sort: PageAbleSort,
    val offset: Int,
    val pageSize: Int,
    val pageNumber: Int,
    val paged: Boolean,
    @SerializedName("unpaged")
    val unPaged: Boolean
)

data class PageAbleSort(
    val empty: Boolean,
    val sorted: Boolean,
    val unsorted: Boolean
)