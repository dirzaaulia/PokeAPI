package com.dirzaaulia.pokeapi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
  val id: Int? = null,
  val name: String? = null,
  val height: Int? = null,
  val weight: Int? = null,
  val baseExperiences: Int? = null,
  val moves: List<Moves>? = null,
  val abilities: List<Abilities>? = null,
  val types: List<Types>? = null,
  val stats: List<Stats>? = null

): Parcelable

@Parcelize
data class Moves(
  val move: CommonStandardItem? = null
): Parcelable

@Parcelize
data class Abilities(
  val ability: CommonStandardItem? = null
): Parcelable

@Parcelize
data class Types(
  val type: CommonStandardItem? = null
): Parcelable

@Parcelize
data class Stats(
  val baseStat: Int? = null,
  val effort: Int? = null,
  val stat: CommonStandardItem? = null
): Parcelable