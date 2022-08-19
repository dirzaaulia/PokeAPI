package com.dirzaaulia.pokeapi.data.service

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dirzaaulia.pokeapi.data.response.PokemonDetailResponse
import com.dirzaaulia.pokeapi.data.response.PokemonListResponse
import com.dirzaaulia.pokeapi.data.util.BASE_URL
import com.dirzaaulia.pokeapi.data.util.LIMIT_API_LIST
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface Service {

  @GET("pokemon")
  suspend fun getPokemonList(
    @Query("offset") offset: Int,
    @Query("limit") limit: Int = LIMIT_API_LIST
  ): Response<PokemonListResponse>

  @GET("pokemon/{id}")
  suspend fun getPokemonDetail(
    @Path("id") id: Int
  ): Response<PokemonDetailResponse>

  companion object {
    fun create(context: Context): Service {
      val chuckerInterceptor = ChuckerInterceptor.Builder(context)
        .collector(ChuckerCollector(context))
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(false)
        .build()

      val client = OkHttpClient.Builder()
        .addInterceptor(chuckerInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

      val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

      return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(Service::class.java)
    }
  }
}