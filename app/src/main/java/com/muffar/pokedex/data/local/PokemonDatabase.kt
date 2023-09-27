package com.muffar.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muffar.pokedex.domain.model.Pokemon

@Database(entities = [Pokemon::class, RemoteKeys::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}