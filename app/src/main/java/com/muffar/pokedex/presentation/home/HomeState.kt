package com.muffar.pokedex.presentation.home

import androidx.paging.PagingData
import com.muffar.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val searchQuery: String = "",
    val pokemon: Flow<PagingData<Pokemon>>? = null
)