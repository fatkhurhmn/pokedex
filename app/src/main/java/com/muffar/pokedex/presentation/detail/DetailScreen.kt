package com.muffar.pokedex.presentation.detail

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.muffar.pokedex.presentation.detail.components.DetailContent
import com.muffar.pokedex.ui.common.DetailShimmer
import com.muffar.pokedex.ui.common.MainScaffold

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: DetailState,
    navigateUp: () -> Unit,
) {
    MainScaffold(
        modifier = modifier,
        titleAppBar = "Pokemon",
        onBackNavigationClick = navigateUp
    ) {
        state.data?.let { pokemon ->
            DetailContent(pokemonDetail = pokemon)
        }

        state.isLoading.let { isLoading ->
            if (isLoading) DetailShimmer()
        }
        state.errorMassage?.let { message ->
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        }
    }
}