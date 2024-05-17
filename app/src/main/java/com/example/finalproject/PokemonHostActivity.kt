package com.example.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.databinding.ActivityPokemonHostBinding

class PokemonHostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPokemonHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PokemonList())
                .commit()
        }
    }
}
