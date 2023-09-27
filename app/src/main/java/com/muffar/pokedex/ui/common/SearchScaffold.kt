package com.muffar.pokedex.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import com.muffar.pokedex.R

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScaffold(
    modifier: Modifier = Modifier,
    titleAppBar: String,
    onTextChange: (String) -> Unit,
    showBackNavigation: Boolean = true,
    onBackNavigationClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    val searchState = rememberSearchState()
    Scaffold(
        topBar = {
            if (searchState.isOpenSearch) {
                SearchAppBar(
                    searchState = searchState,
                    onTextChange = onTextChange
                )
            } else {
                TopAppBar(
                    text = titleAppBar,
                    onBackNavigationClick = onBackNavigationClick,
                    showBackNavigation = showBackNavigation,
                    actionsMenu = {
                        SearchIconButton { searchState.isOpenSearch = true }
                    }
                )
            }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    searchState: SearchState,
    onTextChange: (String) -> Unit,
) {
    TopAppBar(
        title = {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(searchState.focusRequester),
                value = searchState.text,
                maxLines = 1,
                onValueChange = {
                    searchState.text = it
                    onTextChange(it)
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_hint_appbar),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp
                        )
                    )
                },
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        searchState.hideKeyboard()
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = MaterialTheme.colorScheme.background,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
                ),
            )
        },
        actions = {
            if (searchState.text.isNotEmpty()) {
                IconButton(
                    onClick = {
                        searchState.text = ""
                        onTextChange("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    searchState.isOpenSearch = false
                    searchState.text = ""
                    onTextChange("")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
    )
    LaunchedEffect(Unit) {
        searchState.focusRequester.requestFocus()
    }
}