package com.dirzaaulia.pokeapi.data.dto

import com.dirzaaulia.pokeapi.domain.model.Abilities

data class AbilitiesDto(
  val ability: CommonStandardItemDto? = null
) {
  companion object {
    fun transformList(list: List<AbilitiesDto>?): List<Abilities> {
      val transformedList = ArrayList<Abilities>()

      list?.map {
        val item = transform(it)
        transformedList.add(item)
      }

      return transformedList.toList()
    }

    private fun transform(dto: AbilitiesDto?): Abilities {
      return Abilities(
        ability = CommonStandardItemDto.transform(dto?.ability)
      )
    }
  }
}