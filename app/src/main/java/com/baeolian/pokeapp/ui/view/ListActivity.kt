package com.baeolian.pokeapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baeolian.pokeapp.R
import com.baeolian.pokeapp.core.Internet
import com.baeolian.pokeapp.ui.PokemonAdapter
import com.baeolian.pokeapp.ui.viewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class ListActivity : AppCompatActivity() {

    private lateinit var listViewModel : ListViewModel
    private var initializedUI : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]

        initializeNetworkWatchdog()
    }

    private fun initializeNetworkWatchdog(){
        val layoutInflater = findViewById<View>(R.id.networkError)
        val internet= Internet(applicationContext)

        internet.observeForever { isConnected ->
            if (isConnected) {
                if (!initializedUI){
                    initializeUI()
                }
                layoutInflater.visibility = View.GONE
            } else {
                layoutInflater.visibility = View.VISIBLE
                layoutInflater.bringToFront()
            }
        }
    }

    private fun initializeUI(){
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = PokemonAdapter{
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        listViewModel.getPokemonList()

        listViewModel.pokemonList.observe(this) { list ->
            (recyclerView.adapter as PokemonAdapter).setData(list)
        }

        initializedUI = true
    }
}