package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.adapter.PokemonEvolutionAdapter
import com.example.finalproject.adapter.PokemonTypeAdapter
import com.example.finalproject.model.entity.Pokemon

class PokemonDetail : Fragment() {

    internal lateinit var pokemon_img:ImageView

    internal lateinit var pokemon_name:TextView
    internal lateinit var pokemon_height:TextView
    internal lateinit var pokemon_weight:TextView

    lateinit var recycler_type:RecyclerView
    lateinit var recycler_weakness:RecyclerView
    lateinit var recycler_prev_evo:RecyclerView
    lateinit var recycler_next_evo:RecyclerView


    companion object {
        internal var instance:PokemonDetail?=null

        fun getInstance():PokemonDetail{
            if(instance == null)
                instance = PokemonDetail()
            return instance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        val pokemon: Pokemon?
        val args = arguments
        if (args != null) {
            if (args.getString("num") == null)
                pokemon = com.example.finalproject.common.Common.pokemonList[args.getInt("position")]
            else
                pokemon = com.example.finalproject.common.Common.findPokemonByNum(args.getString("num"))
        } else {
            // Handle the case when arguments are null
            pokemon = null
        }

        pokemon_img = itemView.findViewById(R.id.pokemon_image) as ImageView
        pokemon_name = itemView.findViewById(R.id.pokemon_name) as TextView
        pokemon_height = itemView.findViewById(R.id.height) as TextView
        pokemon_weight = itemView.findViewById(R.id.weight) as TextView

        recycler_next_evo = itemView.findViewById(R.id.recycler_next_evo) as RecyclerView
        recycler_next_evo.setHasFixedSize(true)
        recycler_next_evo.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recycler_prev_evo = itemView.findViewById(R.id.recycler_prev_evo) as RecyclerView
        recycler_prev_evo.setHasFixedSize(true)
        recycler_prev_evo.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recycler_type = itemView.findViewById(R.id.recycler_type) as RecyclerView
        recycler_type.setHasFixedSize(true)
        recycler_type.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recycler_weakness = itemView.findViewById(R.id.recycler_weakness) as RecyclerView
        recycler_weakness.setHasFixedSize(true)
        recycler_weakness.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        setDetailPokemon(pokemon)

        return itemView
    }

    private fun setDetailPokemon(pokemon: Pokemon?) {
        Glide.with(requireActivity()).load(pokemon!!.img).into(pokemon_img)

        pokemon_name.text = pokemon.name
        pokemon_height.text = "Height: "+pokemon.height
        pokemon_weight.text = "Weight: "+pokemon.weight

        val typeAdapter = PokemonTypeAdapter(requireActivity(), pokemon.type!!)
        recycler_type.adapter = typeAdapter

        val weaknessAdapter = PokemonTypeAdapter(requireActivity(), pokemon.weaknesses!!)
        recycler_weakness.adapter = weaknessAdapter

        if(pokemon.prev_evolution != null) {
            val prevEvolutionAdapter = PokemonEvolutionAdapter(requireActivity(), pokemon.prev_evolution!!)
            recycler_prev_evo.adapter = prevEvolutionAdapter
        }

        if (pokemon.next_evolution != null) {
            val nextEvolutionAdapter = PokemonEvolutionAdapter(requireActivity(), pokemon.next_evolution!!)
            recycler_next_evo.adapter = nextEvolutionAdapter
        }
    }

}