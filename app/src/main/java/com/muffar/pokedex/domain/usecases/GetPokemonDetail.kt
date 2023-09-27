package com.muffar.pokedex.domain.usecases

import com.muffar.pokedex.domain.model.PokemonDetail
import com.muffar.pokedex.domain.repository.PokemonRepository
import com.muffar.pokedex.utils.Response
import kotlinx.coroutines.flow.Flow

class GetPokemonDetail(
    private val pokemonRepository: PokemonRepository,
) {
    operator fun invoke(id: Int): Flow<Response<PokemonDetail>> {
        return pokemonRepository.getPokemonDetail(id)
    }
}