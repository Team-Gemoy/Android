package com.synrgy.wefly.data.api.json.confirmotp

/**
 * Created by Yosua on 15/01/2024
 */
data class ConfirmOTPResponse(
    val code: Int,
    val data: String?,
    val message: String?,
    val error: String?
)
