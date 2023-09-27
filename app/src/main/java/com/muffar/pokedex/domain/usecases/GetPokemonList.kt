package com.muffar.pokedex.domain.usecases

import androidx.paging.PagingData
import com.muffar.pokedex.domain.model.Pokemon
import com.muffar.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonList(
    private val pokemonRepository: PokemonRepository,
) {
    operator fun invoke(): Flow<PagingData<Pokemon>> {
        return pokemonRepository.getPokemonList()
    }
}