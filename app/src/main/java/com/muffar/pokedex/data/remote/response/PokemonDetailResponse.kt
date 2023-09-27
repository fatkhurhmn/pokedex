package com.muffar.pokedex.data.remote.response

data class PokemonDetailResponse(
    val abilities: List<Ability>,
    val id: Int,
    val name: String,
    val sprites: Sprites,
)