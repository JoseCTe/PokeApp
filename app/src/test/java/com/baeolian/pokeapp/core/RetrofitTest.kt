package com.baeolian.pokeapp.core

import org.junit.Test

class RetrofitTest {

    @Test
    fun `check if base url is correct`() {
        val instance: retrofit2.Retrofit = Retrofit.getRetrofitPokeApi()
        assert(instance.baseUrl().url().toString() == "https://pokeapi.co/api/v2/")
    }
}