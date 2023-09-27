package com.muffar.pokedex.presentation.detail

sealed class DetailEvent {
    data class FetchData(val id:Int) : DetailEvent()
}
