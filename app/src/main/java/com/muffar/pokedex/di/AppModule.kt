package com.muffar.pokedex.di

import com.muffar.pokedex.domain.repository.PokemonRepository
import com.muffar.pokedex.domain.usecases.GetPokemonList
import com.muffar.pokedex.domain.usecases.PokemonUseCases
import com.muffar.pokedex.domain.usecases.SearchPokemon
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providePokemonUseCases(
        pokemonRepository: PokemonRepository,
    ): PokemonUseCases =
        PokemonUseCases(
            getPokemonList = GetPokemonList(pokemonRepository),
            searchPokemon = SearchPokemon(pokemonRepository)
        )
}