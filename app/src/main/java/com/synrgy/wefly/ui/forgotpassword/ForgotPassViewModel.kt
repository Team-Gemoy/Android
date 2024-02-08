package com.synrgy.wefly.ui.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.forgetpassword.ForgotPassRequest
import com.synrgy.wefly.data.api.json.forgetpassword.ForgotPassResponse
import com.synrgy.wefly.data.api.json.forgetpassword.changepassword.ChangePasswordRequest
import com.synrgy.wefly.data.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPassViewModel @Inject constructor(
    private val repo: AuthRepositoryImpl
) : ViewModel() {

    private val _password: MutableStateFlow<ApiResult<ForgotPassResponse>> =
        MutableStateFlow(ApiResult.Loading())
    val password: StateFlow<ApiResult<ForgotPassResponse>> = _password
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading())

    private val _otp: MutableStateFlow<ApiResult<ForgotPassResponse>> =
        MutableStateFlow(ApiResult.Loading())
    val otp: StateFlow<ApiResult<ForgotPassResponse>> = _otp
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading())

    private val _newPass: MutableStateFlow<ApiResult<ForgotPassResponse>> =
        MutableStateFlow(ApiResult.Loading())
    val newPass: StateFlow<ApiResult<ForgotPassResponse>> = _newPass
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading())

    fun forgotPass(passRequest: ForgotPassRequest) = viewModelScope.launch {
        repo.forgotPassword(passRequest).collect {
            _password.value = it
        }
    }

    fun checkOtp(otp: String) = viewModelScope.launch {
        repo.passOtp(otp).collect {
            _otp.value = it
        }
    }

    fun changePass(passBody: ChangePasswordRequest) = viewModelScope.launch {
        repo.changePass(passBody).collect {
            _newPass.value = it
        }
    }
}