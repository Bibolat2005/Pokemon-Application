package com.example.finalproject.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.Interface.IItemClickListener
import com.example.finalproject.R
import com.example.finalproject.common.Common
import com.example.finalproject.model.entity.Pokemon

class PokemonListAdapter(internal var context: Context, internal var pokemonList: List<Pokemon>):
    RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        internal var img_pokemon: ImageView
        internal var txt_pokemon: TextView

        internal var itemCLickListener: IItemClickListener?=null

        fun setItemClickListener(iItemClickListener: IItemClickListener){
            this.itemCLickListener = iItemClickListener
        }
        init{
            img_pokemon = itemView.findViewById(R.id.pokemon_image) as ImageView
            txt_pokemon = itemView.findViewById(R.id.pokemon_name) as TextView

            itemView.setOnClickListener{ view -> itemCLickListener?.onClick(view,adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        Glide.with(context).load(pokemon.img).into(holder.img_pokemon)
        holder.txt_pokemon.text = pokemon.name

        holder.setItemClickListener(object: IItemClickListener{
            override fun onClick(view: View, position: Int) {
//                Toast.makeText(context, "Click at Pokemon: " + pokemonList[position], Toast.LENGTH_SHORT).show()
                LocalBroadcastManager.getInstance(context)
                    .sendBroadcast(Intent(Common.KEY_ENABLE_HOME).putExtra("position", position))
            }
        })
    }

}