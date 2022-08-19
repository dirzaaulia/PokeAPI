package com.dirzaaulia.pokeapi.data.dto

import com.dirzaaulia.pokeapi.domain.model.Moves

data class MovesDto(
  val move: CommonStandardItemDto? = null
) {
  companion object {
    fun transformList(list: List<MovesDto>?): List<Moves> {
      val transformedList = ArrayList<Moves>()

      list?.map {
        val item = transform(it)
        transformedList.add(item)
      }

      return transformedList.toList()
    }

    private fun transform(dto: MovesDto?): Moves {
      return Moves(
        move = CommonStandardItemDto.transform(dto?.move)
      )
    }
  }
}