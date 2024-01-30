package com.synrgy.wefly.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.airport.list.AirportListResponse
import com.synrgy.wefly.data.repository.AirportRepositoryImpl
import com.synrgy.wefly.data.repository.PreferenceRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val prefRepo: PreferenceRepositoryImpl,
    private val repo: AirportRepositoryImpl
) : ViewModel() {

    private val _airportList: MutableStateFlow<ApiResult<AirportListResponse>> =
        MutableStateFlow(ApiResult.Loading())

    val airportList: StateFlow<ApiResult<AirportListResponse>> = _airportList
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading())

    val token = prefRepo.getToken()

    fun getToken() {
        prefRepo.getToken()
    }

    fun setToken(token: String?) = viewModelScope.launch {
        if (token != null) {
            prefRepo.setToken(token)
        }
    }

    fun getAirportList() =
        viewModelScope.launch {
            repo.getAirportList().onStart { _airportList.value = ApiResult.Loading() }
                .collect {
                    _airportList.value = it
                }
        }

}