package com.muffar.pokedex.utils

sealed class Response<out T> {
    data class Success<out T>(val value: T) : Response<T>()
    data class Error(val message: String) : Response<Nothing>()
    object Loading : Response<Nothing>()
    object Empty : Response<Nothing>()
}