package com.synrgy.wefly.domain

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.login.LoginRequest
import com.synrgy.wefly.data.api.login.LoginResponse
import com.synrgy.wefly.data.api.register.RegisterRequest
import com.synrgy.wefly.data.api.register.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(loginRequest: LoginRequest): Flow<ApiResult<LoginResponse>>

    fun register(registerRequest: RegisterRequest): Flow<ApiResult<RegisterResponse>>
}