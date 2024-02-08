package com.synrgy.wefly.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.profile.ProfileResponse
import com.synrgy.wefly.data.repository.PreferenceRepositoryImpl
import com.synrgy.wefly.data.repository.ProfileRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repo: PreferenceRepositoryImpl,
    private val profileRepo: ProfileRepositoryImpl
): ViewModel() {

    private val _profile: MutableStateFlow<ApiResult<HeaderResponse<ProfileResponse>>> =
        MutableStateFlow(ApiResult.Loading())

    val profile: StateFlow<ApiResult<HeaderResponse<ProfileResponse>>> = _profile
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading())

    val token = repo.getToken()

    fun getProfile() = viewModelScope.launch {
        _profile.value = ApiResult.Loading()
        _profile.value = profileRepo.getProfile()

    }

    fun logout() = viewModelScope.launch {
        repo.clearToken()
    }

}