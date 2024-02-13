package com.synrgy.wefly.domain

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.forgetpassword.ForgotPassRequest
import com.synrgy.wefly.data.api.json.forgetpassword.ForgotPassResponse
import com.synrgy.wefly.data.api.json.forgetpassword.changepassword.ChangePasswordRequest
import com.synrgy.wefly.data.api.json.login.LoginRequest
import com.synrgy.wefly.data.api.json.login.LoginResponse
import com.synrgy.wefly.data.api.json.register.AccountRequest
import com.synrgy.wefly.data.api.json.register.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(loginRequest: LoginRequest): Flow<ApiResult<LoginResponse>>

    fun register(accountRequest: AccountRequest): Flow<ApiResult<RegisterResponse>>

    fun forgotPassword(password: ForgotPassRequest): Flow<ApiResult<ForgotPassResponse>>

    fun passOtp(token: String): Flow<ApiResult<ForgotPassResponse>>

    fun changePass(changeBody: ChangePasswordRequest): Flow<ApiResult<ForgotPassResponse>>

}