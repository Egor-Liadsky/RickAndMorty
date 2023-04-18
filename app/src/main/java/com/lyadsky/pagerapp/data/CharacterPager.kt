package com.lyadsky.pagerapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lyadsky.pagerapp.data.entity.Result
import com.lyadsky.pagerapp.data.service.CharacterService

class CharacterPager(
    private val characterService: CharacterService
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val page = params.key ?: 1
            val response = characterService.getCharacters(page)
            LoadResult.Page(
                data = response.results,
                prevKey = if(page == 1) null else page.minus(1),
                nextKey = if(response.results.isEmpty()) null else page.plus(1)
            )
        } catch (ex: Exception){
            LoadResult.Error(ex)
        }
    }
}
