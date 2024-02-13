package com.synrgy.wefly.data.api

data class HeaderResponse<T>(
    val code: Int,
    val data: T,
    val message: String
)
