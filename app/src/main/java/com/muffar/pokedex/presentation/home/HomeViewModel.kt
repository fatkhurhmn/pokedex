package com.muffar.pokedex.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.muffar.pokedex.domain.usecases.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonUseCases: PokemonUseCases,
) : ViewModel() {

    private var _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        onEvent(HomeEvent.GetPokemons)
    }
    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetPokemons -> getPokemons()
            is HomeEvent.SearchPokemon -> searchPokemon()
            is HomeEvent.UpdateSearchQuery->{
                _state.value = _state.value.copy(searchQuery = event.query)
            }
        }
    }


    fun getPokemons() {
        val pokemons = pokemonUseCases.getPokemonList().cachedIn(viewModelScope)
        _state.value = state.value.copy(pokemon = pokemons)
    }

    fun searchPokemon() {
        val pokemons =
            pokemonUseCases.searchPokemon(_state.value.searchQuery).cachedIn(viewModelScope)
        _state.value = _state.value.copy(pokemon = pokemons)
    }
}