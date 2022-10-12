package com.baeolian.pokeapp.ui.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.baeolian.pokeapp.R
import com.baeolian.pokeapp.ui.Utils
import com.baeolian.pokeapp.ui.viewModel.PokemonViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        viewModel = ViewModelProvider(this)[PokemonViewModel::class.java]

        initializeUI()
    }

    @SuppressLint("SetTextI18n")
    private fun initializeUI(){
        val id = intent.extras?.get("id") as Int

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            var name = pokemon.name.replaceFirstChar { it.uppercase() }
            name = Utils.checkSpecialChar(name)
            pokemonName.text = name
            dexTextView.text = R.string.dexText.toString() + " ${pokemon.id}"

            val circularProgressDrawable = CircularProgressDrawable(this)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.setColorSchemeColors(Color.GRAY)
            circularProgressDrawable.start()

            Glide.with(this)
                .load(pokemon.sprites.other.officialArtwork.image)
                .placeholder(circularProgressDrawable)
                .into(imageView)

            Glide.with(this)
                .load(pokemon.sprites.frontDefault)
                .placeholder(circularProgressDrawable)
                .into(imageNormal)

            Glide.with(this)
                .load(pokemon.sprites.frontShiny)
                .placeholder(circularProgressDrawable)
                .into(imageShiny)
        })
    }
}