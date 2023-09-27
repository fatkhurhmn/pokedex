package com.muffar.pokedex.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.pokedex.domain.usecases.PokemonUseCases
import com.muffar.pokedex.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val pokemonUseCases: PokemonUseCases,
    handle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> = _state

    init {
        handle.get<String>("id")?.let {
            onEvent(DetailEvent.FetchData(it.toInt()))
        }
    }

    private fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.FetchData -> getPokemonDetail(event.id)
        }
    }

    private fun getPokemonDetail(id: Int) {
        viewModelScope.launch {
            pokemonUseCases.getPokemonDetail(id).collect {
                when (it) {
                    is Response.Success -> _state.value = DetailState(data = it.value)
                    is Response.Loading -> _state.value = DetailState(isLoading = true)
                    is Response.Error -> _state.value =
                        DetailState(hasError = true, errorMassage = it.message)

                    else -> {}
                }
            }
        }
    }
}