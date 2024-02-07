package com.synrgy.wefly.data.repository

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.profile.ProfileResponse
import com.synrgy.wefly.data.api.service.ApiService
import com.synrgy.wefly.domain.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProfileRepository {
    override suspend fun getProfile(): ApiResult<HeaderResponse<ProfileResponse>> = try {
        val response = apiService.getUserProfile()
        (ApiResult.Success(response))
    }catch (e: Throwable) {
        ApiResult.Error(e.message ?: "Error get profile")
    }

}