package com.dirzaaulia.pokeapi.data.response

import com.dirzaaulia.pokeapi.data.dto.CommonStandardItemDto
import com.dirzaaulia.pokeapi.domain.model.CommonListStandardItem
import com.squareup.moshi.Json

data class PokemonListResponse(
  @Json(name = "results")
  val data: List<CommonStandardItemDto>? = null
) {
  companion object {
    fun transform(response: PokemonListResponse): CommonListStandardItem {
      return CommonListStandardItem(
        list = CommonStandardItemDto.transformList(response.data)
      )
    }
  }
}