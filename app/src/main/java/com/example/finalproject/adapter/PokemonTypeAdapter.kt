package com.example.finalproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.common.Common

class PokemonTypeAdapter(internal var context:Context, internal var typeList: List<String>):RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        internal var chip: com.robertlevonyan.views.chip.Chip


        init {
            chip = itemView.findViewById(R.id.chip) as com.robertlevonyan.views.chip.Chip
            chip.setOnClickListener { Toast.makeText(context, "Clicked",Toast.LENGTH_SHORT).show()}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.chip.setText(typeList[position])
        holder.chip.setBackgroundColor(Common.getColorByType(typeList[position]))
    }

}