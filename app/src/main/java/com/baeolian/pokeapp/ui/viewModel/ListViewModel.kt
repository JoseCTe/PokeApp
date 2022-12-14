package com.baeolian.pokeapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baeolian.pokeapp.core.Retrofit.getRetrofitPokeApi
import com.baeolian.pokeapp.data.model.PokemonModel
import com.baeolian.pokeapp.data.model.ResponseModel
import com.baeolian.pokeapp.data.network.PokemonApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {

    private val service: PokemonApiClient = getRetrofitPokeApi().create(PokemonApiClient::class.java)

    val pokemonList = MutableLiveData<List<PokemonModel>>()

    fun getPokemonList(limit: Int, offset: Int){
        val call = service.getPokemonList(limit,offset)

        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                response.body()?.results?.let { list ->
                    pokemonList.postValue(list)
                }
            }
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                call.cancel()
            }
        })
    }
}