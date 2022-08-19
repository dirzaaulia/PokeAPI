package com.dirzaaulia.pokeapi.data.database

import androidx.paging.PagingSource
import androidx.room.*
import com.dirzaaulia.pokeapi.domain.model.FavoritePokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDao {

  @Insert(
    entity = FavoritePokemon::class,
    onConflict = OnConflictStrategy.REPLACE
  )
  suspend fun addFavoritePokemon(favoritePokemon: FavoritePokemon)

  @Delete(entity = FavoritePokemon::class)
  suspend fun deleteFavoritePokemon(favoritePokemon: FavoritePokemon)

  @Query("SELECT * FROM favorite_pokemon_table")
  fun getFavoritePokemonList(): PagingSource<Int, FavoritePokemon>

  @Query("SELECT * FROM favorite_pokemon_table WHERE id = :id LIMIT 1")
  fun checkIfPokemonFavorited(id: Int) : Flow<FavoritePokemon>
}