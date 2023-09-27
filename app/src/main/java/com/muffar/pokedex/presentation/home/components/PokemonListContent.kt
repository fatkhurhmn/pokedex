package com.muffar.pokedex.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.muffar.pokedex.domain.model.Pokemon
import com.muffar.pokedex.ui.common.EmptyScreen
import com.valentinilk.shimmer.shimmer

@Composable
fun PokemonListContent(
    modifier: Modifier = Modifier,
    pokemons: LazyPagingItems<Pokemon>,
    onClick: (Pokemon) -> Unit,
) {
    val handlePagingResult = handlePokemonResult(pokemons = pokemons)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(
                count = pokemons.itemCount,
            ) {
                pokemons[it]?.let { pokemon ->
                    PokemonCard(
                        pokemon = pokemon,
                        onClick = { data ->
                            onClick(data)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun handlePokemonResult(
    pokemons: LazyPagingItems<Pokemon>,
): Boolean {
    val loadState = pokemons.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(30) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .height(40.dp)
                            .clip(MaterialTheme.shapes.small)
                            .shimmer()
                            .background(color = MaterialTheme.colorScheme.outlineVariant),
                    )
                }
            }
            false
        }

        error != null -> {
            EmptyScreen(error)
            false
        }

        else -> true
    }
}