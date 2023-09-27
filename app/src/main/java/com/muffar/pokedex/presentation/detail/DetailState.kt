package com.muffar.pokedex.presentation.detail

import com.muffar.pokedex.domain.model.PokemonDetail

data class DetailState(
    val data: PokemonDetail? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMassage: String? = null,
)