package com.muffar.pokedex.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    titleAppBar: String,
    showBackNavigation: Boolean = true,
    onBackNavigationClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                text = titleAppBar,
                onBackNavigationClick = onBackNavigationClick,
                showBackNavigation = showBackNavigation,
            )
        },
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            content()
        }
    }
}