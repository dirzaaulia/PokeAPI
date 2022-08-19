package com.dirzaaulia.pokeapi.screens.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dirzaaulia.pokeapi.BuildConfig
import com.dirzaaulia.pokeapi.R
import com.dirzaaulia.pokeapi.databinding.ActivityDetailBinding
import com.dirzaaulia.pokeapi.domain.model.Moves
import com.dirzaaulia.pokeapi.domain.model.Pokemon
import com.dirzaaulia.pokeapi.domain.model.Stats
import com.dirzaaulia.pokeapi.domain.util.*
import com.dirzaaulia.pokeapi.screens.detail.adapter.MovesAdapter
import com.dirzaaulia.pokeapi.screens.detail.adapter.StatsAdapter
import com.dirzaaulia.pokeapi.util.capitalizeWords
import com.dirzaaulia.pokeapi.util.loadNetworkImage
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

  private lateinit var binding: ActivityDetailBinding

  private val viewModel: DetailViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setup()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.detail_menu, menu)
    return true
  }

  override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
    val menuItem = menu?.getItem(0)
    val drawable = if (viewModel.isPokemonFavoritedState.value) {
      ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
    } else {
      ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
    }
    menuItem?.icon = drawable

    return super.onPrepareOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.menu_favorite -> {
        //TODO mark as favorite and change icon
        if (viewModel.isPokemonFavoritedState.value) {
          viewModel.markPokemonUnfavorite()
          Snackbar.make(binding.root, "${viewModel.pokemonName.value} mark as unfavorite", Snackbar.LENGTH_SHORT).show()
        } else {
          viewModel.markPokemonFavorite()
          Snackbar.make(binding.root, "${viewModel.pokemonName.value} mark as favorite", Snackbar.LENGTH_SHORT).show()
        }
        true
      }
      else -> false
    }
  }

  private fun setup() {
    loadExtra()
    subscribePokemonState()
    subscribeFavoritePokemonState()
  }

  private fun loadExtra() {
    intent?.let {
      val id = it.getIntExtra(ID, 0)
      viewModel.apply {
        setPokemonId(id)
        getPokemonDetail()
        checkIfPokemonFavorited()
      }
    }
  }

  private fun subscribePokemonState() {
    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.CREATED) {
        viewModel.pokemonState.collect { state ->
          setViewState(state)
          when {
            state.isSucceeded -> {
              state.success {
               setViewStateSuccess(it)
              }
            }
            state.isError -> {
              state.error {
                setViewStateError(it)
              }
            }
          }
        }
      }
    }
  }

  private fun subscribeFavoritePokemonState() {
    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.isPokemonFavoritedState.collect {
          invalidateOptionsMenu()
        }
      }
    }
  }

  private fun setViewState(states: ResponseResult<Pokemon?>) {
    binding.apply {
      content.isVisible = states.isSucceeded
      emptyView.apply {
        root.isVisible = states.isLoading || states.isError
        progressBar.isVisible = states.isLoading
        description.isVisible = states.isError
        button.isVisible = states.isError
      }
    }
  }

  private fun setViewStateSuccess(pokemon: Pokemon?) {
    supportActionBar?.title = pokemon?.name?.capitalizeWords()

    binding.apply {
      image.loadNetworkImage(
        context = this@DetailActivity,
        url = getString(R.string.image_url_format, pokemon?.id.toString())
      )

      height.text = getString(R.string.pokemon_height_format, pokemon?.height)
      weight.text = getString(R.string.pokemon_weight_format, pokemon?.weight)
      baseExperiences.text = "${pokemon?.baseExperiences}"

      val lastIndexTypes = pokemon?.types?.size?.minus(1)
      var types = ""
      pokemon?.types?.forEachIndexed { index, item ->
        types += if (index != lastIndexTypes) {
          "${item.type?.name?.capitalizeWords()} | "
        } else {
          item.type?.name?.capitalizeWords() ?: ""
        }
      }
      this.types.text = types

      val lastIndexAbilities = pokemon?.abilities?.size?.minus(1)
      var abilities = ""
      pokemon?.abilities?.forEachIndexed { index, item ->
        abilities += if (index != lastIndexAbilities) {
          "${item.ability?.name?.capitalizeWords()} | "
        } else {
          item.ability?.name?.capitalizeWords() ?: ""
        }
      }
      this.abilities.text = abilities

      setupStatsAdapter(pokemon?.stats)
      setupMovesAdapter(pokemon?.moves)
    }
  }
  private fun setupStatsAdapter(stats: List<Stats>?) {
    val adapter = StatsAdapter().apply {
      submitList(stats)
    }
    binding.recyclerViewStats.adapter = adapter
  }

  private fun setupMovesAdapter(moves: List<Moves>?) {
    val adapter = MovesAdapter().apply {
      submitList(moves)
    }
    binding.recyclerViewMoves.adapter = adapter
  }

  private fun setViewStateError(throwable: Throwable) {
    binding.emptyView.apply {
      description.text = throwable.message
      button.setOnClickListener {
        viewModel.getPokemonDetail()
      }
    }
  }

  companion object {
    private const val ID = BuildConfig.APPLICATION_ID + ".ID"

    fun newIntent(context: Context, id: Int): Intent {
      val intent = Intent(context, DetailActivity::class.java).apply {
        putExtra(ID, id)
      }
      return intent
    }
  }
}