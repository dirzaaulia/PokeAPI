package com.dirzaaulia.pokeapi.data.source

import androidx.annotation.WorkerThread
import androidx.paging.PagingData
import com.dirzaaulia.pokeapi.data.response.PokemonDetailResponse
import com.dirzaaulia.pokeapi.data.response.PokemonListResponse
import com.dirzaaulia.pokeapi.data.service.Service
import com.dirzaaulia.pokeapi.data.util.REMOTE_DATASOURCE_NOT_IMPLEMENTED
import com.dirzaaulia.pokeapi.domain.model.CommonStandardItem
import com.dirzaaulia.pokeapi.domain.model.FavoritePokemon
import com.dirzaaulia.pokeapi.domain.util.ResponseResult
import com.dirzaaulia.pokeapi.domain.util.executeWithResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class ServiceRemoteDataSourceImpl(
  private val service: Service
): ServiceDataSource {

  override suspend fun getPokemonList(offset: Int): ResponseResult<List<CommonStandardItem>> {
    return withContext(Dispatchers.IO) {
      executeWithResponse {
        val response = service.getPokemonList(offset)
        response.body()?.let {
          val data = PokemonListResponse.transform(it)
          data.list ?: emptyList()
        } ?: run {
          throw HttpException(response)
        }
      }
    }
  }

  @WorkerThread
  override fun getPokemonDetail(id: Int) = flow {
    emit(ResponseResult.Loading)
    try {
      val response = service.getPokemonDetail(id)
      response.body()?.let {
        val data = PokemonDetailResponse.transform(it)
        emit(ResponseResult.Success(data))
      } ?: run {
        throw HttpException(response)
      }
    } catch (throwable: Throwable) {
      emit(ResponseResult.Error(throwable))
    }
  }.flowOn(Dispatchers.IO)

  override fun getFavoritePokemonList(): Flow<PagingData<FavoritePokemon>> {
    throw IllegalArgumentException(REMOTE_DATASOURCE_NOT_IMPLEMENTED)
  }

  override fun checkIfPokemonFavorited(pokemonId: Int): Flow<FavoritePokemon?> {
    throw IllegalArgumentException(REMOTE_DATASOURCE_NOT_IMPLEMENTED)
  }

  override suspend fun markPokemonFavorite(favoritePokemon: FavoritePokemon) {
    throw IllegalArgumentException(REMOTE_DATASOURCE_NOT_IMPLEMENTED)
  }

  override suspend fun markPokemonUnfavorite(favoritePokemon: FavoritePokemon) {
    throw IllegalArgumentException(REMOTE_DATASOURCE_NOT_IMPLEMENTED)
  }
}