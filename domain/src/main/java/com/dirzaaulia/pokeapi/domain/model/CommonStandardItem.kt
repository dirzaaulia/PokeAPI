package com.dirzaaulia.pokeapi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommonListStandardItem(
  val list: List<CommonStandardItem>? = null
): Parcelable

@Parcelize
data class CommonStandardItem(
  val name: String? = null,
  val url: String? = null
): Parcelable