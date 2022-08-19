package com.dirzaaulia.pokeapi.data.dto

import com.dirzaaulia.pokeapi.domain.model.Types

data class TypesDto(
  val type: CommonStandardItemDto? = null
) {
  companion object {
    fun transformList(list: List<TypesDto>?): List<Types> {
      val transformedList = ArrayList<Types>()

      list?.map {
        val item = transform(it)
        transformedList.add(item)
      }

      return transformedList.toList()
    }

    private fun transform(dto: TypesDto?): Types {
      return Types(
        type = CommonStandardItemDto.transform(dto?.type)
      )
    }
  }
}