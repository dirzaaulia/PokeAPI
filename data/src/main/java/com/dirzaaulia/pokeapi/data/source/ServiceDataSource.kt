package com.dirzaaulia.pokeapi.data.source

import androidx.paging.PagingData
import com.dirzaaulia.pokeapi.domain.model.CommonStandardItem
import com.dirzaaulia.pokeapi.domain.model.FavoritePokemon
import com.dirzaaulia.pokeapi.domain.model.Pokemon
import com.dirzaaulia.pokeapi.domain.util.ResponseResult
import kotlinx.coroutines.flow.Flow

interface ServiceDataSource {

  suspend fun getPokemonList(offset: Int): ResponseResult<List<CommonStandardItem>>

  fun getPokemonDetail(id: Int): Flow<ResponseResult<Pokemon>>

  fun getFavoritePokemonList(): Flow<PagingData<FavoritePokemon>>

  fun checkIfPokemonFavorited(pokemonId: Int): Flow<FavoritePokemon?>

  suspend fun markPokemonFavorite(favoritePokemon: FavoritePokemon)

  suspend fun markPokemonUnfavorite(favoritePokemon: FavoritePokemon)
}