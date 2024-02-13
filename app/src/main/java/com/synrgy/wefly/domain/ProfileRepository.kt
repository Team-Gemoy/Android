package com.synrgy.wefly.domain

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.profile.ProfileResponse
import com.synrgy.wefly.data.api.json.register.AccountRequest

interface ProfileRepository {
    suspend fun getProfile(): ApiResult<HeaderResponse<ProfileResponse>>

    suspend fun editProfile(profileRequest: AccountRequest): ApiResult<HeaderResponse<ProfileResponse>>
}