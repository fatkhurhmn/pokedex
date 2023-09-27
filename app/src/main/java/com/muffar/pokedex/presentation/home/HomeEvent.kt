package com.muffar.pokedex.presentation.home

sealed class HomeEvent {
    object GetPokemons : HomeEvent()
    object SearchPokemon : HomeEvent()
    data class UpdateSearchQuery(val query: String) : HomeEvent()
}
