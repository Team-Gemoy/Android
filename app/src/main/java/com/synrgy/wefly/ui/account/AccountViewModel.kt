package com.synrgy.wefly.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.wefly.data.repository.PreferenceRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repo: PreferenceRepositoryImpl
): ViewModel() {

    val token = repo.getToken()

    fun logout() = viewModelScope.launch {
        repo.clearToken()
    }

}