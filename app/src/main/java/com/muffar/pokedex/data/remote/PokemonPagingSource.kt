package com.muffar.pokedex.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muffar.pokedex.domain.model.Pokemon
import com.muffar.pokedex.utils.DataMapper

class PokemonPagingSource(
    private val pokemonApi: PokemonApi,
) : PagingSource<Int, Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val page = params.key ?: 1
            val response = pokemonApi.getPokemonList(page = page)
            val data = response.results.map { DataMapper.mapPokemonResponseToPokemon(it) }
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }    }
}