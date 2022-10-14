package com.baeolian.pokeapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baeolian.pokeapp.R
import com.baeolian.pokeapp.core.Utils.checkSpecialChar
import com.baeolian.pokeapp.core.Utils.newLoadingCircle
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

        display = checkSpecialChar(display)

        val realPosition = position + 1
        Glide.with(holder.itemView)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/$realPosition.png")
            .placeholder(newLoadingCircle(holder.itemView.context, 25f))
            .into(holder.itemView.imageView)

        holder.itemView.pokemonText.text = display
        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}
