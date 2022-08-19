package com.dirzaaulia.pokeapi.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dirzaaulia.pokeapi.data.paging.PokemonPagingSource
import com.dirzaaulia.pokeapi.data.util.LIMIT_API_LIST
import com.dirzaaulia.pokeapi.domain.model.CommonStandardItem
import com.dirzaaulia.pokeapi.domain.model.FavoritePokemon
import com.dirzaaulia.pokeapi.domain.model.Pokemon
import com.dirzaaulia.pokeapi.domain.repository.ServiceRepository
import com.dirzaaulia.pokeapi.domain.util.ResponseResult
import kotlinx.coroutines.flow.Flow

class ServiceRepositoryImpl(
  private val localDataSource: ServiceDataSource,
  private val remoteDataSource: ServiceDataSource
): ServiceRepository {
  override fun getPokemonList(): Flow<PagingData<CommonStandardItem>> {
    return Pager(PagingConfig(pageSize = LIMIT_API_LIST)) {
      PokemonPagingSource(remoteDataSource = remoteDataSource)
    }.flow
  }

  override fun getPokemonDetail(id: Int): Flow<ResponseResult<Pokemon>> {
    return remoteDataSource.getPokemonDetail(id)
  }

  override fun getFavoritePokemonList(): Flow<PagingData<FavoritePokemon>> {
    return localDataSource.getFavoritePokemonList()
  }

  override fun checkIfPokemonFavorited(pokemonId: Int): Flow<FavoritePokemon?> {
    return localDataSource.checkIfPokemonFavorited(pokemonId)
  }

  override suspend fun markPokemonFavorite(favoritePokemon: FavoritePokemon) {
    return localDataSource.markPokemonFavorite(favoritePokemon)
  }

  override suspend fun markPokemonUnfavorite(favoritePokemon: FavoritePokemon) {
    return localDataSource.markPokemonUnfavorite(favoritePokemon)
  }
}