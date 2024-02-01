package com.synrgy.wefly.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.wefly.common.PreferenceDefaults
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.transaction.TransactionListResponse
import com.synrgy.wefly.data.api.transaction.TransactionRequest
import com.synrgy.wefly.data.repository.PreferenceRepositoryImpl
import com.synrgy.wefly.data.repository.TransactionRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repo: TransactionRepositoryImpl,
    private val prefRepo: PreferenceRepositoryImpl
) : ViewModel() {
    private val _transactionFLow: MutableStateFlow<ApiResult<HeaderResponse<TransactionListResponse>>> =
        MutableStateFlow(ApiResult.Loading())
    val transactionFlow: StateFlow<ApiResult<HeaderResponse<TransactionListResponse>>> = _transactionFLow.asStateFlow()

    private val _getId: MutableStateFlow<ApiResult<HeaderResponse<TransactionListResponse>>> =
        MutableStateFlow(ApiResult.Loading())
    val getId: StateFlow<ApiResult<HeaderResponse<TransactionListResponse>>> = _getId.asStateFlow()


    val token = prefRepo.getToken()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = PreferenceDefaults.TOKEN,
        )

    fun postTransaction(transactionRequest: TransactionRequest) = viewModelScope.launch {
        prefRepo.getToken().collect {token ->
            repo.saveTransaction(transactionRequest, header = "Bearer $token").collect(){
                _transactionFLow.value = it
            }
        }
    }

    fun getTransaction(id: String) = viewModelScope.launch {
        prefRepo.getToken().collect {token ->
            repo.getTransaction(id = id, header = "Bearer $token").collect(){
                _getId.value = it
            }
        }

    }
}