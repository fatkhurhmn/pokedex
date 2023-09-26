package com.muffar.pokedex.data.remote

import com.muffar.pokedex.data.remote.response.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") page: Int,
        @Query("limit") size: Int = 20,
    ): PokemonListResponse
}