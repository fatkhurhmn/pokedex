package com.muffar.pokedex.di

import com.muffar.pokedex.data.repository.PokemonRepositoryImpl
import com.muffar.pokedex.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providePokemonRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository
}