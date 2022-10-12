package com.baeolian.pokeapp.data.network

import com.baeolian.pokeapp.data.model.PokemonDetails
import com.baeolian.pokeapp.data.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiClient {
    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<ResponseModel>
    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Call<PokemonDetails>
}