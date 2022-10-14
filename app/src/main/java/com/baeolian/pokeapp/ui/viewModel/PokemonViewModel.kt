package com.baeolian.pokeapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baeolian.pokeapp.core.Retrofit.getRetrofitPokeApi
import com.baeolian.pokeapp.data.model.PokemonDetails
import com.baeolian.pokeapp.data.network.PokemonApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel : ViewModel() {

    private val service: PokemonApiClient = getRetrofitPokeApi().create(PokemonApiClient::class.java)

    val pokemonInfo = MutableLiveData<PokemonDetails>()

    fun getPokemonInfo(id: Int){
        val call = service.getPokemonInfo(id)

        call.enqueue(object : Callback<PokemonDetails> {
            override fun onResponse(call: Call<PokemonDetails>, response: Response<PokemonDetails>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)
                }
            }
            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                call.cancel()
            }
        })
    }
}