package com.muffar.pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class Ability(
    @field:SerializedName("ability")
    val ability: AbilityItem,
)