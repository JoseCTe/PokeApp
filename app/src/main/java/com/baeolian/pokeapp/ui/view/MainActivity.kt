package com.baeolian.pokeapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baeolian.pokeapp.R
import com.baeolian.pokeapp.ui.PokemonAdapter
import com.baeolian.pokeapp.ui.viewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var listViewModel : ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]

        initializeUI()
    }

    private fun initializeUI(){
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = PokemonAdapter{
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        listViewModel.getPokemonList()

        listViewModel.pokemonList.observe(this, Observer { list ->
            (recyclerView.adapter as PokemonAdapter).setData(list)
        })
    }
}