package com.baeolian.pokeapp.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.baeolian.pokeapp.core.Utils.getOrAwaitValue
import com.baeolian.pokeapp.data.model.PokemonDetails
import org.junit.Rule
import org.junit.Test

class PokemonViewModelTest {

    private var pokemonViewModel: PokemonViewModel = PokemonViewModel()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `check if index value gets the desired pokemon`(){
        //Given
        val index = 25
        val expectedPokemonName = "pikachu"

        //When
        //When
        pokemonViewModel.getPokemonInfo(index)

        //Then
        val res: PokemonDetails? = pokemonViewModel.pokemonInfo.getOrAwaitValue()

        assert(res != null)
        assert((res?.name ?: "missingNo") == expectedPokemonName)
    }
}