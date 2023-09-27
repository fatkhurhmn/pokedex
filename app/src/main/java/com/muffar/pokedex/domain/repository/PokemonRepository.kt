package com.muffar.pokedex.domain.repository

import androidx.paging.PagingData
import com.muffar.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>
}