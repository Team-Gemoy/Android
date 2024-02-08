package com.synrgy.wefly.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.ContentResponse
import com.synrgy.wefly.data.api.json.transaction.TransactionListResponse
import com.synrgy.wefly.data.repository.OrderRepositoryImpl
import com.synrgy.wefly.data.repository.PreferenceRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repo: PreferenceRepositoryImpl,
    private val orderRepo: OrderRepositoryImpl
): ViewModel() {
    val token = repo.getToken()

    private val _history: MutableStateFlow<ApiResult<HeaderResponse<ContentResponse<TransactionListResponse>>>> =
        MutableStateFlow(ApiResult.Loading())

    val history: StateFlow<ApiResult<HeaderResponse<ContentResponse<TransactionListResponse>>>> = _history
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading())


    fun getHistory() = viewModelScope.launch {
        _history.value = ApiResult.Loading()
        _history.value = orderRepo.getHistory()

    }
}