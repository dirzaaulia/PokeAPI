package com.dirzaaulia.pokeapi.data.dto

import com.dirzaaulia.pokeapi.domain.model.CommonStandardItem

data class CommonStandardItemDto(
  val name: String? = null,
  val url: String? = null
) {
  companion object {
    fun transformList(list: List<CommonStandardItemDto>?): List<CommonStandardItem> {
      val transformedList = ArrayList<CommonStandardItem>()

      list?.map {
        val item = transform(it)
        transformedList.add(item  )
      }

      return transformedList.toList()
    }

    fun transform(dto: CommonStandardItemDto?): CommonStandardItem {
      return CommonStandardItem(
        name = dto?.name,
        url = dto?.url
      )
    }
  }
}