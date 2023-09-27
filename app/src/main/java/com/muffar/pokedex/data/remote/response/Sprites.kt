package com.muffar.pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class Sprites(
    @field:SerializedName("front_default")
    val frontDefault: String,
)