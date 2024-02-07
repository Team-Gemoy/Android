package com.synrgy.wefly.ui.order

import androidx.lifecycle.ViewModel
import com.synrgy.wefly.data.repository.PreferenceRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repo: PreferenceRepositoryImpl
): ViewModel() {
    val token = repo.getToken()
}