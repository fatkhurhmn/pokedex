package com.muffar.pokedex.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.muffar.pokedex.domain.usecases.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pokemonUseCases: PokemonUseCases,
) : ViewModel() {
    val pokemonList = pokemonUseCases.getPokemonList().cachedIn(viewModelScope)
}