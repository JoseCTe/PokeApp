package com.baeolian.pokeapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel (
    @Expose @SerializedName("count") val count: Int,
    @Expose @SerializedName("next") val next: String,
    @Expose @SerializedName("previous") val previous: String,
    @Expose @SerializedName("results") val results: List<PokemonModel>
)

data class PokemonModel(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)

data class PokemonDetails(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("weight") val weight: Int,
    @Expose @SerializedName("height") val height: Int,
    @Expose @SerializedName("sprites") val sprites: Sprites
){
    data class Sprites (
        @Expose @SerializedName("front_default") val frontDefault: String?,
        @Expose @SerializedName("front_shiny") val frontShiny: String?,
        @Expose @SerializedName("versions") val versions: Versions,
        @Expose @SerializedName("other") val other: Other
    )

    data class Versions (
        @Expose @SerializedName("generation-vii") val generation7: Gen7
    )

    data class Gen7 (
        @Expose @SerializedName("icons") val icons: Icons
    )

    data class Icons (
        @Expose @SerializedName("front_default") val frontDefault: String?
    )

    data class Other (
        @Expose @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
    )

    data class OfficialArtwork (
        @Expose @SerializedName("front_default") val image: String?
    )
}
