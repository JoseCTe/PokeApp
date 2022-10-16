package com.baeolian.pokeapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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
        val layoutError = findViewById<View>(R.id.networkError)
        val internet= Internet(applicationContext)

        internet.observeForever { isConnected ->
            if (isConnected) {
                if (!initializedUI){
                    initializeUI()
                } else{
                    unlockUI()
                }

                layoutError.visibility = View.GONE

            } else {
                layoutError.visibility = View.VISIBLE
                layoutError.bringToFront()
                blockUI()
            }
        }
    }

    private fun initializeUI(){
        unlockUI()
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

    private fun blockUI(){
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun unlockUI(){
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}