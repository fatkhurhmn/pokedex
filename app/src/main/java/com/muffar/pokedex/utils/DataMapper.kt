package com.muffar.pokedex.utils

import com.muffar.pokedex.data.remote.response.PokemonDetailResponse
import com.muffar.pokedex.data.remote.response.PokemonResponse
import com.muffar.pokedex.domain.model.Pokemon
import com.muffar.pokedex.domain.model.PokemonDetail

object DataMapper {
    fun mapPokemonResponseToPokemon(value: PokemonResponse): Pokemon {
        val parts = value.url.split("/")
        val id = parts[6].toInt()
        return Pokemon(
            id = id,
            name = value.name
        )
    }

    fun mapPokemonDetailResponseToPokemonDetail(value: PokemonDetailResponse): PokemonDetail {
        val abilites = value.abilities.map { it.ability.name }
        val image = value.sprites.frontDefault
        return PokemonDetail(
            id = value.id,
            name = value.name,
            abilities = abilites,
            image = image
        )
    }
}