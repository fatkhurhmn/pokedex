package com.muffar.pokedex.domain.usecases

data class PokemonUseCases(
    val getPokemonList: GetPokemonList,
    val searchPokemon: SearchPokemon,
    val getPokemonDetail: GetPokemonDetail
)