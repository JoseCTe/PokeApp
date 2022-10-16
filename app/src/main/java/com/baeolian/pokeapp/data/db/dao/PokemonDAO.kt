package com.baeolian.pokeapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baeolian.pokeapp.data.db.entity.PokemonEntity

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM pokemon_table ORDER BY id ASC")
    suspend fun getAllPokemon():List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemon:List<PokemonEntity>)
}