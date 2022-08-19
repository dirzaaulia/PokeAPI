package com.dirzaaulia.pokeapi.data.dto

import com.dirzaaulia.pokeapi.domain.model.Stats
import com.squareup.moshi.Json

data class StatsDto(
  @Json(name = "base_stat")
  val baseStat: Int? = null,
  val effort: Int? = null,
  val stat: CommonStandardItemDto? = null
) {
  companion object {
    fun transformList(list: List<StatsDto>?): List<Stats> {
      val transformedList = ArrayList<Stats>()

      list?.map {
        val item = transform(it)
        transformedList.add(item)
      }

      return transformedList.toList()
    }

    private fun transform(dto: StatsDto?): Stats {
      return Stats(
        baseStat = dto?.baseStat,
        effort = dto?.effort,
        stat = CommonStandardItemDto.transform(dto?.stat)
      )
    }
  }
}