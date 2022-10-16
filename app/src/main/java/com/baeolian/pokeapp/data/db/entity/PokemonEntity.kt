package com.baeolian.pokeapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class PokemonEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "weight") val weight: Int,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "images") val images: String
)
