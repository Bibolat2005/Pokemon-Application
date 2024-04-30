package com.example.finalproject.model.network
import com.example.finalproject.model.entity.Pokedex
import retrofit2.http.GET

interface PokemonService {
    @get:GET("pokedex.json")
    val listPokemon:io.reactivex.Observable<Pokedex>
}