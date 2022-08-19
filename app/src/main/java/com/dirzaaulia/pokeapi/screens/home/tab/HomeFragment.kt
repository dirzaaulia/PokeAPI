package com.dirzaaulia.pokeapi.screens.home.tab

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.dirzaaulia.pokeapi.common.adapter.CommonLoadStateAdapter
import com.dirzaaulia.pokeapi.databinding.FragmentHomeBinding
import com.dirzaaulia.pokeapi.domain.model.CommonStandardItem
import com.dirzaaulia.pokeapi.screens.detail.DetailActivity
import com.dirzaaulia.pokeapi.screens.home.HomeActivity
import com.dirzaaulia.pokeapi.screens.home.HomeViewModel
import com.dirzaaulia.pokeapi.screens.home.adapter.HomeAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeAdapter.HomeAdapterListener {

  private lateinit var binding: FragmentHomeBinding
  private val adapter = HomeAdapter(this)

  private val viewModel: HomeViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    binding = FragmentHomeBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupView()
  }

  override fun onItemClicked(item: CommonStandardItem) {
    val uri = Uri.parse(item.url)
    val id = uri.pathSegments[uri.pathSegments.size - 1] ?: "0"
    if (id != "0") {
      val intent = DetailActivity.newIntent(activity as HomeActivity, id.toInt())
      startActivity(intent)
    } else {
      Snackbar.make(binding.root, "Can't get Pokemon detail", Snackbar.LENGTH_SHORT).show()
    }
  }

  private fun setupView() {
    setupAdapter()
    subscribePokemonList()
  }

  private fun subscribePokemonList() {
    lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
        viewModel.pokemonList.collectLatest {
          adapter.submitData(it)
        }
      }
    }
  }

  private fun setupAdapter() {
    binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
      CommonLoadStateAdapter { adapter.retry() },
      CommonLoadStateAdapter { adapter.retry() }
    )

    adapter.addLoadStateListener { loadStates ->
      setViewState(loadStates)

      when (loadStates.source.refresh) {
        is LoadState.Error -> {
          val throwable = (loadStates.source.refresh as LoadState.Error).error
          setViewStateError(throwable.message.toString())
        }
        else -> {}
      }
    }
  }

  private fun setViewState(loadStates: CombinedLoadStates) {
    binding.apply {
      recyclerView.isVisible = loadStates.source.refresh is LoadState.NotLoading
      emptyView.apply {
        root.isVisible = loadStates.source.refresh !is LoadState.NotLoading
        progressBar.isVisible = loadStates.source.refresh is LoadState.Loading
        description.isVisible = loadStates.source.refresh is LoadState.Error
        button.isVisible = loadStates.source.refresh is LoadState.Error
      }
    }
  }

  private fun setViewStateError(errorMessage: String) {
    binding.emptyView.apply {
      description.text = errorMessage
      button.setOnClickListener {
        adapter.refresh()
      }
    }
  }
}