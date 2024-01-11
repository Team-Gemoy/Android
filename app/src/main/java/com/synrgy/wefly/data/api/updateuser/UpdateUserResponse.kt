package com.synrgy.wefly.data.api.updateuser

import com.google.gson.annotations.SerializedName

data class UpdateUserResponse(
    val code: String,
    val data: UserData
)

data class UserData(
    val createdDate: String,
    val deletedDate: String?,
    val updatedDate: String,
    val id: Int,
    val username: String,
    val fullName: String,
    val gender: String?,
    val city: String,
    @SerializedName("dateOfBirth")
    val dob: String,
    val phoneNumber: String,
    val otp: String,
    val otpExpiredDate: String,
    val roles: ArrayList<Roles>,
    val authorities: ArrayList<Authorities>,
    val message: String
)

data class Roles(
    val id: Int,
    val name: String,
    val type: String,
    val rolePaths: ArrayList<RolePaths>
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