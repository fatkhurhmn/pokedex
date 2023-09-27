package com.muffar.pokedex.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey
    val id: Int,
    val name: String,
)