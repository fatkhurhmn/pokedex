package com.muffar.pokedex.utils

import com.muffar.pokedex.data.remote.response.PokemonResponse
import com.muffar.pokedex.domain.model.Pokemon

object DataMapper {
    fun mapPokemonResponseToPokemon(value: PokemonResponse): Pokemon {
        val parts = value.url.split("/")
        val id = parts[6].toInt()
        return Pokemon(
            id = id,
            name = value.name
        )
    }
}