package com.muffar.pokedex.domain.repository

import androidx.paging.PagingData
import com.muffar.pokedex.domain.model.Pokemon
import com.muffar.pokedex.domain.model.PokemonDetail
import com.muffar.pokedex.utils.Response
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>
    fun searchPokemon(query: String): Flow<PagingData<Pokemon>>
    fun getPokemonDetail(id:Int):Flow<Response<PokemonDetail>>
}