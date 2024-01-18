package com.synrgy.wefly.ui.order

import androidx.lifecycle.ViewModel
import com.synrgy.wefly.data.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repo: AuthRepositoryImpl
): ViewModel() {
}