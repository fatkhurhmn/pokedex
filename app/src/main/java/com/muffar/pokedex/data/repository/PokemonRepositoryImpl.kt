package com.muffar.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.muffar.pokedex.data.remote.PokemonApi
import com.muffar.pokedex.data.remote.PokemonPagingSource
import com.muffar.pokedex.domain.model.Pokemon
import com.muffar.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi,
) : PokemonRepository {
    override suspend fun getPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                PokemonPagingSource(pokemonApi)
            }
        ).flow
    }
}