package com.synrgy.wefly.domain

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.login.LoginRequest
import com.synrgy.wefly.data.api.json.login.LoginResponse
import com.synrgy.wefly.data.api.json.register.RegisterRequest
import com.synrgy.wefly.data.api.json.register.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(loginRequest: LoginRequest): Flow<ApiResult<LoginResponse>>

    fun register(registerRequest: RegisterRequest): Flow<ApiResult<RegisterResponse>>

}