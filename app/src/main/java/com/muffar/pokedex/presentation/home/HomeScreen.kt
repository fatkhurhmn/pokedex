package com.muffar.pokedex.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.muffar.pokedex.presentation.home.components.PokemonListContent
import com.muffar.pokedex.ui.common.SearchScaffold

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigate: () -> Unit,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
) {
    SearchScaffold(
        titleAppBar = "Pokemon",
        onTextChange = {
            if (it.isNotEmpty()) {
                onEvent(HomeEvent.UpdateSearchQuery(it))
                onEvent(HomeEvent.SearchPokemon)
            }else{
                onEvent(HomeEvent.GetPokemons)
            }
        },
        showBackNavigation = false
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            state.pokemon?.collectAsLazyPagingItems()?.let {
                PokemonListContent(pokemons = it, onClick = { navigate() })
            }
        }
    }
}