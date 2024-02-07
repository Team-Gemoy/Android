package com.synrgy.wefly.domain

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.profile.ProfileResponse

interface ProfileRepository {
    suspend fun getProfile(): ApiResult<HeaderResponse<ProfileResponse>>
}