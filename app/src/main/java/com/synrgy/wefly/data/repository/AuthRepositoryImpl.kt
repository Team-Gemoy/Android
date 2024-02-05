package com.synrgy.wefly.data.repository

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.login.LoginRequest
import com.synrgy.wefly.data.api.json.login.LoginResponse
import com.synrgy.wefly.data.api.json.register.RegisterRequest
import com.synrgy.wefly.data.api.json.register.RegisterResponse
import com.synrgy.wefly.data.api.service.AuthService
import com.synrgy.wefly.domain.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: AuthService,
): AuthRepository {

    override fun login(loginRequest: LoginRequest): Flow<ApiResult<LoginResponse>> =
        flow {
            emit(ApiResult.Loading())
            val request = apiService.login(loginRequest).await()
            emit(ApiResult.Success(request))
        }.catch {
            emit(ApiResult.Error(it.message ?: "Login error"))
        }

    override fun register(registerRequest: RegisterRequest): Flow<ApiResult<RegisterResponse>> =
        flow {
            emit(ApiResult.Loading())
            val request = apiService.register(registerRequest).await()
            emit(ApiResult.Success(request))
        }.catch {
            emit(ApiResult.Error(it.message ?: "Register error"))
        }
}