package com.muffar.pokedex.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.muffar.pokedex.presentation.home.components.PokemonListContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigate: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val pokemons = viewModel.pokemonList.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            Text(
                text = "Pokemon",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 20.sp
                ),
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        },
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                PokemonListContent(pokemons = pokemons, onClick = { navigate() })
            }
        }
    }
}