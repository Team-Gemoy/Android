package com.synrgy.wefly.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.wefly.common.PreferenceDefaults
import com.synrgy.wefly.data.repository.PreferenceRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repo: PreferenceRepositoryImpl
): ViewModel() {

    val token = repo.getToken()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = PreferenceDefaults.TOKEN,
        )

    fun logout() = viewModelScope.launch {
        repo.clearToken()
    }

}