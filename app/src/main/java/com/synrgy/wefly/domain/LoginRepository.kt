package com.synrgy.wefly.domain

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.login.LoginRequest
import com.synrgy.wefly.data.api.login.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(loginRequest: LoginRequest): Flow<ApiResult<LoginResponse>>
}