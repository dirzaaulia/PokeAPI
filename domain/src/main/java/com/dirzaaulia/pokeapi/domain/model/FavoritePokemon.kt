package com.dirzaaulia.pokeapi.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_pokemon_table")
@Parcelize
data class FavoritePokemon(
  @PrimaryKey
  val id: Int? = 0,
  val name: String? = ""
): Parcelable