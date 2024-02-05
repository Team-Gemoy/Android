package com.synrgy.wefly.data.api.json.updateuser

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(
    val fullName: String,
    val city: String,
    @SerializedName("dateOfBirth")
    val dob: String
)
