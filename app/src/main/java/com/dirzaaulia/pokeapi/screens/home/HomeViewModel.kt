package com.dirzaaulia.pokeapi.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dirzaaulia.pokeapi.domain.model.FavoritePokemon
import com.dirzaaulia.pokeapi.domain.usecase.ServiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val serviceUseCase: ServiceUseCase
): ViewModel() {

  val pokemonList = serviceUseCase.getPokemonList().cachedIn(viewModelScope)
  val favoriteList = serviceUseCase.getFavoritePokemonList().cachedIn(viewModelScope)

  fun markPokemonUnfavorite(favoritePokemon: FavoritePokemon) {
    viewModelScope.launch {
      serviceUseCase.markPokemonUnfavorite(favoritePokemon)
    }
  }
}