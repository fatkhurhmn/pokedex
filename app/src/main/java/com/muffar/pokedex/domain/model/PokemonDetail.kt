package com.muffar.pokedex.domain.model

data class PokemonDetail(
    val id: Int,
    val abilities: List<String>,
    val name: String,
    val image: String,
)