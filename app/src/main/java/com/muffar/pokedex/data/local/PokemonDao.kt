package com.muffar.pokedex.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muffar.pokedex.domain.model.Pokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<Pokemon>)

    @Query("SELECT * FROM pokemon")
    fun getPokemons(): PagingSource<Int, Pokemon>

    @Query("DELETE FROM pokemon")
    suspend fun deleteAllPokemons()

    @Query("SELECT * FROM pokemon WHERE name LIKE :query")
    fun searchPokemon(query: String): PagingSource<Int, Pokemon>
}