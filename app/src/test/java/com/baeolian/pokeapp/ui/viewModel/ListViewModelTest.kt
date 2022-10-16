package com.baeolian.pokeapp.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.baeolian.pokeapp.core.Utils.getOrAwaitValue
import com.baeolian.pokeapp.data.model.PokemonModel
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {

    private var listViewModel: ListViewModel = ListViewModel()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `check if a specific pokemon is in the list created by the view`(){
        //Given
        val pokemon = PokemonModel("pikachu", "https://pokeapi.co/api/v2/pokemon/25/")

        //When
        listViewModel.getPokemonList(30, 0)

        //Then
        val res: PokemonModel? = listViewModel.pokemonList.getOrAwaitValue().find {
            it.name == pokemon.name
        }

        assert(res == pokemon)
    }
}