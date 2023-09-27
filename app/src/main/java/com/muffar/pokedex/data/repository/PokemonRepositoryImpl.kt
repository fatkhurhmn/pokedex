package com.muffar.pokedex.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.muffar.pokedex.data.local.PokemonDatabase
import com.muffar.pokedex.data.remote.PokemonRemoteMediator
import com.muffar.pokedex.data.remote.PokemonApi
import com.muffar.pokedex.domain.model.Pokemon
import com.muffar.pokedex.domain.model.PokemonDetail
import com.muffar.pokedex.domain.repository.PokemonRepository
import com.muffar.pokedex.utils.DataMapper
import com.muffar.pokedex.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
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

    override fun getPokemonDetail(id: Int): Flow<Response<PokemonDetail>> = flow {
        emit(Response.Loading)
        try {
            val response = pokemonApi.getPokemonDetail(id)
            val data = DataMapper.mapPokemonDetailResponseToPokemonDetail(response)
            emit(Response.Success(data))
        } catch (e: Exception) {
            when (e) {
                is HttpException -> emit(Response.Error("Terjadi kesalahan jaringan"))
                else -> emit(Response.Error(e.message ?: ""))
            }
        }
    }
}