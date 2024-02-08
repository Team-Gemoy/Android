package com.synrgy.wefly.data.api.json.forgetpassword.changepassword

/**
 * Created by Yosua on 15/01/2024
 */
data class ChangePasswordResponse(
    val code: Int,
    val data: String?,
    val message: String?,
    val error: String?
)
