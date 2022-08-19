package com.dirzaaulia.pokeapi.util

import android.content.Context
import android.webkit.WebSettings
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.dirzaaulia.pokeapi.R
import java.util.*

fun ImageView.loadNetworkImage(
  context: Context,
  url: String?,
  loadingStrokeWidth: Float = 5f,
  loadingRadius: Float = 30f,
) {
  if (url.isNullOrEmpty()) {
    this.apply {
      setImageResource(R.drawable.ic_baseline_broken_image_24)
      setBackgroundColor(
        ContextCompat.getColor(context, R.color.purple_700)
      )
      scaleType = ImageView.ScaleType.CENTER_INSIDE
    }
  } else {
    val glideUrl = url.toGlideUrl(context)
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = loadingStrokeWidth
    circularProgressDrawable.centerRadius = loadingRadius
    circularProgressDrawable.setColorSchemeColors(
      ContextCompat.getColor(context, R.color.purple_700)
    )
    circularProgressDrawable.start()

    Glide.with(context)
      .load(glideUrl)
      .placeholder(circularProgressDrawable)
      .error(R.drawable.ic_baseline_broken_image_24)
      .into(this)
  }
}

fun String.capitalizeWords(): String =
  split(" ").joinToString(" ") { word ->
    word
      .lowercase(Locale.getDefault())
      .replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
      }
  }

private fun String.toGlideUrl(context: Context): GlideUrl {
  val imageUrl = if (this.contains("http://", true)) {
    this.replace("http://", "https://")
  } else {
    this
  }

  return GlideUrl(
    imageUrl,
    LazyHeaders.Builder()
      .addHeader("User-Agent", WebSettings.getDefaultUserAgent(context))
      .build()
  )
}