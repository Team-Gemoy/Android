package com.synrgy.wefly.data.api.json.changepassword

/**
 * Created by Yosua on 15/01/2024
 */
data class ChangePasswordRequest(
    val email: String,
    val newPassword: String,
    val confirmPassword: String,
    val otp: String,
)
