package com.dirzaaulia.pokeapi.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dirzaaulia.pokeapi.data.source.ServiceDataSource
import com.dirzaaulia.pokeapi.data.util.LIMIT_API_LIST
import com.dirzaaulia.pokeapi.data.util.STARTING_OFFSET_API
import com.dirzaaulia.pokeapi.domain.model.CommonStandardItem
import com.dirzaaulia.pokeapi.domain.util.pagingSucceeded

class PokemonPagingSource(
  private val remoteDataSource: ServiceDataSource
): PagingSource<Int, CommonStandardItem>() {

  override fun getRefreshKey(state: PagingState<Int, CommonStandardItem>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommonStandardItem> {
    val offset = params.key ?: STARTING_OFFSET_API

    return remoteDataSource.getPokemonList(offset).pagingSucceeded { data ->
      val totalData = data.size
      LoadResult.Page(
        data = data,
        prevKey = if (offset == STARTING_OFFSET_API) null else offset - 20,
        nextKey = if (totalData == LIMIT_API_LIST) offset + 20 else null
      )
    }
  }
}