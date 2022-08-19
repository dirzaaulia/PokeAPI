package com.dirzaaulia.pokeapi.data.response

import com.dirzaaulia.pokeapi.data.dto.AbilitiesDto
import com.dirzaaulia.pokeapi.data.dto.MovesDto
import com.dirzaaulia.pokeapi.data.dto.StatsDto
import com.dirzaaulia.pokeapi.data.dto.TypesDto
import com.dirzaaulia.pokeapi.domain.model.Pokemon
import com.squareup.moshi.Json

data class PokemonDetailResponse(
  val id: Int? = null,
  val name: String? = null,
  val height: Int? = null,
  val weight: Int? = null,
  @Json(name = "base_experience")
  val baseExperiences: Int? = null,
  val moves: List<MovesDto>? = null,
  val abilities: List<AbilitiesDto>? = null,
  val types: List<TypesDto>? = null,
  val stats: List<StatsDto>? = null
) {
  companion object {
    fun transform(response: PokemonDetailResponse?): Pokemon {
      return Pokemon(
        id = response?.id,
        name = response?.name,
        height = response?.height,
        weight = response?.weight,
        baseExperiences = response?.baseExperiences,
        moves = MovesDto.transformList(response?.moves),
        abilities = AbilitiesDto.transformList(response?.abilities),
        types = TypesDto.transformList(response?.types),
        stats = StatsDto.transformList(response?.stats)
      )
    }
  }
}