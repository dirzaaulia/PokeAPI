package com.dirzaaulia.pokeapi.domain.usecase

import androidx.paging.PagingData
import com.dirzaaulia.pokeapi.domain.model.CommonStandardItem
import com.dirzaaulia.pokeapi.domain.model.FavoritePokemon
import com.dirzaaulia.pokeapi.domain.model.Pokemon
import com.dirzaaulia.pokeapi.domain.repository.ServiceRepository
import com.dirzaaulia.pokeapi.domain.util.ResponseResult
import kotlinx.coroutines.flow.Flow

class ServiceUseCaseImpl(
  private val repository: ServiceRepository
): ServiceUseCase {
  override fun getPokemonList(): Flow<PagingData<CommonStandardItem>> {
    return repository.getPokemonList()
  }

  override fun getPokemonDetail(id: Int): Flow<ResponseResult<Pokemon>> {
    return repository.getPokemonDetail(id)
  }

  override fun getFavoritePokemonList(): Flow<PagingData<FavoritePokemon>> {
    return repository.getFavoritePokemonList()
  }

  override fun checkIfPokemonFavorited(pokemonId: Int): Flow<FavoritePokemon?> {
    return repository.checkIfPokemonFavorited(pokemonId)
  }

  override suspend fun markPokemonFavorite(favoritePokemon: FavoritePokemon) {
    return repository.markPokemonFavorite(favoritePokemon)
  }

  override suspend fun markPokemonUnfavorite(favoritePokemon: FavoritePokemon) {
    return repository.markPokemonUnfavorite(favoritePokemon)
  }
}