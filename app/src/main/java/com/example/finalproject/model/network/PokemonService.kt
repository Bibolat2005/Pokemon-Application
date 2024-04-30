package com.example.finalproject.model.network

import android.database.Observable
import com.example.finalproject.model.entity.Pokedex
import com.example.finalproject.model.entity.Pokemon
import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {
    @get:GET("pokedex.json")
    val listPokemon:io.reactivex.Observable<Pokedex>
}