package com.synrgy.wefly.ui.order

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.synrgy.wefly.R
import com.synrgy.wefly.common.repeatCollectionOnCreated
import com.synrgy.wefly.databinding.FragmentOrderBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderFragment : Fragment(R.layout.fragment_order) {
    private lateinit var binding: FragmentOrderBinding

    private val viewModel: OrderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderBinding.bind(view)

        setupUI()
        repeatCollectionOnCreated { tokenRan() }
        viewModel.getHistory()
    }

    private fun setupUI() {
        with(binding) {

        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {

        }
    }

    private fun tokenRan () {
        if (viewModel.token.isEmpty()) {
            gotoLogin()
        }
        Log.d("neotica", "token: ${viewModel.token}")
    }

    private fun gotoLogin() {
        val action = OrderFragmentDirections.actionOrderFragmentToAuthGroup()
        findNavController().navigate(action)
    }
}
