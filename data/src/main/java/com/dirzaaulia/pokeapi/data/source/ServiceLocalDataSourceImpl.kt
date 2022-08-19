package com.dirzaaulia.pokeapi.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dirzaaulia.pokeapi.data.database.DatabaseDao
import com.dirzaaulia.pokeapi.data.util.LIMIT_API_LIST
import com.dirzaaulia.pokeapi.data.util.LOCAL_DATASOURCE_NOT_IMPLEMENTED
import com.dirzaaulia.pokeapi.domain.model.CommonStandardItem
import com.dirzaaulia.pokeapi.domain.model.FavoritePokemon
import com.dirzaaulia.pokeapi.domain.model.Pokemon
import com.dirzaaulia.pokeapi.domain.util.ResponseResult
import kotlinx.coroutines.flow.Flow

class ServiceLocalDataSourceImpl(
  private val databaseDao: DatabaseDao
): ServiceDataSource {
  override suspend fun getPokemonList(offset: Int): ResponseResult<List<CommonStandardItem>> {
    throw IllegalArgumentException(LOCAL_DATASOURCE_NOT_IMPLEMENTED)
  }

  override fun getPokemonDetail(id: Int): Flow<ResponseResult<Pokemon>> {
    throw IllegalArgumentException(LOCAL_DATASOURCE_NOT_IMPLEMENTED)
  }

  override fun getFavoritePokemonList(): Flow<PagingData<FavoritePokemon>> {
    return Pager(config = PagingConfig(pageSize = LIMIT_API_LIST)) {
      databaseDao.getFavoritePokemonList()
    }.flow
  }

  override fun checkIfPokemonFavorited(pokemonId: Int): Flow<FavoritePokemon?> {
    return databaseDao.checkIfPokemonFavorited(pokemonId)
  }

  override suspend fun markPokemonFavorite(favoritePokemon: FavoritePokemon) {
    databaseDao.addFavoritePokemon(favoritePokemon)
  }

  override suspend fun markPokemonUnfavorite(favoritePokemon: FavoritePokemon) {
    databaseDao.deleteFavoritePokemon(favoritePokemon)
  }
}