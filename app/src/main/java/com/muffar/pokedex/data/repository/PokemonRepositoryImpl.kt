package com.muffar.pokedex.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.muffar.pokedex.data.local.PokemonDatabase
import com.muffar.pokedex.data.remote.PokemonRemoteMediator
import com.muffar.pokedex.data.remote.PokemonApi
import com.muffar.pokedex.domain.model.Pokemon
import com.muffar.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PokemonRepositoryImpl @Inject constructor(
    private val database: PokemonDatabase,
    private val pokemonApi: PokemonApi,
) : PokemonRepository {
    override fun getPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PokemonRemoteMediator(pokemonApi = pokemonApi, database = database),
            pagingSourceFactory = {
                database.pokemonDao().getPokemons()
            }
        ).flow
    }

    override fun searchPokemon(query: String): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PokemonRemoteMediator(pokemonApi = pokemonApi, database = database),
            pagingSourceFactory = {
                database.pokemonDao().searchPokemon(query = "%$query%")
            }
        ).flow
    }
}