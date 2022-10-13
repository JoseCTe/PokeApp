package com.baeolian.pokeapp.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.baeolian.pokeapp.R
import com.baeolian.pokeapp.data.model.PokemonModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pokemon_holder.view.*

class PokemonAdapter(val pokemonClick: (Int) -> Unit): RecyclerView.Adapter<PokemonAdapter.SearchViewHolder>() {

    private var pokemonList: List<PokemonModel> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listPokemon: List<PokemonModel>){
        pokemonList = listPokemon
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pokemon_holder, parent,false))
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        var display = "#"

        if(position < 9){
            display += "00"
        } else if(position < 99){
            display += "0"
        }

        display = display + (position + 1) + " - " + pokemon.name.replaceFirstChar { it.uppercase() }

        display = Utils.checkSpecialChar(display)

        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.setColorSchemeColors(Color.parseColor("#F9AA33"))
        circularProgressDrawable.start()

        val realPosition = position + 1
        Glide.with(holder.itemView)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/$realPosition.png")
            .placeholder(circularProgressDrawable)
            .into(holder.itemView.imageView)

        holder.itemView.pokemonText.text = display
        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}

object Utils{
    fun checkSpecialChar(str: String): String {
        var res = str
        if(str.endsWith("-m")){
            res = str.replace("-m", " ♂")
        } else if(str.endsWith("-f")) {
            res = str.replace("-f", " ♀")
        } else if(str.endsWith("-normal")){
            res = str.replace("-normal", "")
        }
        return res
    }
}
