package com.baeolian.pokeapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baeolian.pokeapp.data.model.PokemonModel
import com.baeolian.pokeapp.data.model.ResponseModel
import com.baeolian.pokeapp.data.network.PokemonApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListViewModel : ViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokemonApiClient = retrofit.create(PokemonApiClient::class.java)

    val pokemonList = MutableLiveData<List<PokemonModel>>()

    fun getPokemonList(){
        val call = service.getPokemonList(386,0)

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