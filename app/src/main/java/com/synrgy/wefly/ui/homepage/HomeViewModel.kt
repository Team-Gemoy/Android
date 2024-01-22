package com.synrgy.wefly.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.wefly.data.repository.PreferenceRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: PreferenceRepositoryImpl
): ViewModel() {

    val token = repo.getToken()

    fun getToken() {
        repo.getToken()
    }

    fun setToken(token: String?) = viewModelScope.launch {
        if (token != null) {
            repo.setToken(token)
        }
    }

}