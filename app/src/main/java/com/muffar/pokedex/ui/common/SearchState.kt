package com.muffar.pokedex.ui.common

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController

class SearchState @OptIn(ExperimentalComposeUiApi::class) constructor(
    private val keyboardController: SoftwareKeyboardController?,
    val focusRequester: FocusRequester,
    isOpenSearch: Boolean,
    text: String,
) {
    var isOpenSearch by mutableStateOf(isOpenSearch)
    var text by mutableStateOf(text)

    @OptIn(ExperimentalComposeUiApi::class)
    fun hideKeyboard() {
        keyboardController?.hide()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberSearchState(
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    focusRequester: FocusRequester = FocusRequester(),
    isOpenSearch: Boolean = false,
    text: String = "",
): SearchState = remember {
    SearchState(
        keyboardController = keyboardController,
        focusRequester = focusRequester,
        isOpenSearch = isOpenSearch,
        text = text,
    )
}