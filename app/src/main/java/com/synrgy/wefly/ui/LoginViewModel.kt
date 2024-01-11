package com.synrgy.wefly.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.login.LoginRequest
import com.synrgy.wefly.data.api.login.LoginResponse
import com.synrgy.wefly.data.repository.LoginRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: LoginRepositoryImpl
): ViewModel() {
    private val _loginStateFlow: MutableStateFlow<ApiResult<LoginResponse>> =
        MutableStateFlow(ApiResult.Loading())
    val loginStateFlow: StateFlow<ApiResult<LoginResponse>> = _loginStateFlow.asStateFlow()

    fun login(loginRequest: LoginRequest) = viewModelScope.launch {
        repo.login(loginRequest).collect {
            _loginStateFlow.value = it
        }
    }
}