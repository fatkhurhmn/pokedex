package com.muffar.pokedex.data.remote.response

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonResponse>
)