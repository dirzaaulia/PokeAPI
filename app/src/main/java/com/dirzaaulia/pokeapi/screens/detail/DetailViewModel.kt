package com.dirzaaulia.pokeapi.screens.detail

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirzaaulia.pokeapi.domain.model.FavoritePokemon
import com.dirzaaulia.pokeapi.domain.model.Pokemon
import com.dirzaaulia.pokeapi.domain.usecase.ServiceUseCase
import com.dirzaaulia.pokeapi.domain.util.ResponseResult
import com.dirzaaulia.pokeapi.domain.util.success
import com.dirzaaulia.pokeapi.util.capitalizeWords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val serviceUseCase: ServiceUseCase
): ViewModel() {

  private val _pokemonId = MutableStateFlow(0)
  private val _pokemonName = MutableStateFlow("")
  val pokemonName = _pokemonName.asStateFlow()

  private val _pokemonState: MutableStateFlow<ResponseResult<Pokemon?>> =
    MutableStateFlow(ResponseResult.Success(null))
  val pokemonState = _pokemonState.asStateFlow()

  private val _favoritePokemonState: MutableStateFlow<FavoritePokemon?> =
    MutableStateFlow(null)

  private val _isPokemonFavoritedState: MutableStateFlow<Boolean> =
    MutableStateFlow(false)
  val isPokemonFavoritedState = _isPokemonFavoritedState.asStateFlow()

  @MainThread
  fun setPokemonId(id: Int) {
    _pokemonId.value = id
  }

  fun getPokemonDetail() {
    serviceUseCase.getPokemonDetail(_pokemonId.value)
      .onEach {
        _pokemonState.value = it
        it.success { pokemon ->
          _pokemonName.value = pokemon.name.toString().capitalizeWords()

          val favoritePokemon = FavoritePokemon(
            id = pokemon.id,
            name = pokemon.name
          )
          _favoritePokemonState.value = favoritePokemon
        }
      }
      .launchIn(viewModelScope)
  }

  fun checkIfPokemonFavorited() {
    serviceUseCase.checkIfPokemonFavorited(_pokemonId.value)
      .onEach {
        if (it != null) {
          _favoritePokemonState.value = it
          _isPokemonFavoritedState.value = it.id != null || it.id != 0
        } else {
          _isPokemonFavoritedState.value = false
        }
      }
      .launchIn(viewModelScope)
  }

  fun markPokemonFavorite() {
    viewModelScope.launch {
      _favoritePokemonState.value?.let { serviceUseCase.markPokemonFavorite(it) }
      checkIfPokemonFavorited()
    }
  }

  fun markPokemonUnfavorite() {
    viewModelScope.launch {
       _favoritePokemonState.value?.let { serviceUseCase.markPokemonUnfavorite(it) }
      checkIfPokemonFavorited()
    }
  }
}