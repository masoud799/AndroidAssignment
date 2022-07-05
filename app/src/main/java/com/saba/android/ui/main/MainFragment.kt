package com.saba.android.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.saba.android.databinding.FragmentMainBinding
import com.saba.base.BaseFragment
import com.saba.presentation.contract.MainContract
import com.saba.presentation.vm.MainViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Detail Fragment
 */
@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel : MainViewModel by viewModels()
    private val adapter : CommentAdapter by lazy {
        CommentAdapter()
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.rvMovies.adapter = adapter
        initObservers()
        // Fetch movies when search button clicked
        binding.btnSearch.setOnClickListener {
            if (!binding.edtSearch.text.isNullOrEmpty()){
                viewModel.setEvent(MainContract.Event.OnSearchButtonClicked(query = binding.edtSearch.text.toString()))
            }
        }
    }

    /**
     * Initialize Observers
     */
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.searchState) {
                        is MainContract.SearchState.Idle -> {
                            binding.loadingPb.isVisible = false
                        }
                        is MainContract.SearchState.Loading -> {
                            binding.loadingPb.isVisible = true
                        }
                        is MainContract.SearchState.Success -> {
                            val data = state.searchResult.data
                            adapter.submitList(data)
                            binding.loadingPb.isVisible = false
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is MainContract.Effect.ShowError -> {
                            val msg = it.message
                        }
                    }
                }
            }
        }
    }

}