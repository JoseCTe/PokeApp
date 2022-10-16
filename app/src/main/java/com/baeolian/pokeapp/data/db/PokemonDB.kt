package com.baeolian.pokeapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baeolian.pokeapp.data.db.dao.PokemonDAO
import com.baeolian.pokeapp.data.db.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDB: RoomDatabase() {

    abstract fun getPokemonDao():PokemonDAO
}