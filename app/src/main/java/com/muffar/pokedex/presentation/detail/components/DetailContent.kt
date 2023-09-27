package com.muffar.pokedex.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.muffar.pokedex.R
import com.muffar.pokedex.domain.model.PokemonDetail

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    pokemonDetail: PokemonDetail,
) {
    val ctx = LocalContext.current
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = ImageRequest.Builder(ctx)
                .data(pokemonDetail.image)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
        )

        Text(
            text = pokemonDetail.name,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 30.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Ability",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = pokemonDetail.abilities.joinToString(", "),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}