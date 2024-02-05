package com.synrgy.wefly.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.register.RegisterRequest
import com.synrgy.wefly.data.api.json.register.RegisterResponse
import com.synrgy.wefly.data.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Yosua on 18/01/2024
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepo: AuthRepositoryImpl,
) : ViewModel() {

    private val _registerStateFlow: MutableStateFlow<ApiResult<RegisterResponse>> =
        MutableStateFlow(ApiResult.Loading())
    val registerStateFlow: StateFlow<ApiResult<RegisterResponse>> = _registerStateFlow.asStateFlow()

    fun register(registerRequest: RegisterRequest) = viewModelScope.launch {
        authRepo.register(registerRequest).collect {
            _registerStateFlow.value = it
        }
    }

}