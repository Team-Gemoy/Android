package com.synrgy.wefly.data.api.json.profile

data class ProfileResponse(
    val createdDate: String,
    val deletedDate: String,
    val updatedDate: String,
    val id: Int,
    val provider: String,
    val username: String,
    val fullName: String,
    val gender: String?,
    val city: String,
    val dateOfBirth: String,
    val phoneNumber: String,
    val otp: String?,
    val authorities: ArrayList<Authorities>
)

data class Authorities(
    val id: Int,
    val name: String,
    val type: String,
    val rolePaths: ArrayList<RolePaths>
)

data class RolePaths(
    val id: Int,
    val name: String,
    val pattern: String,
    val method: String
)