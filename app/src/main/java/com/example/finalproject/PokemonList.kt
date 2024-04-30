package com.example.finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapter.PokemonListAdapter
import com.example.finalproject.common.Common
import com.example.finalproject.common.ItemOffsetDecoration
import com.example.finalproject.databinding.FragmentPokemonListBinding
import com.example.finalproject.model.network.ApiClient
import com.example.finalproject.model.network.PokemonService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokemonList : Fragment() {
    internal var compositeDisposable = CompositeDisposable()
    internal var iPokemonList: PokemonService
    private lateinit var pokemonRecyclerView: RecyclerView

    init {
        val retrofit = ApiClient.instance
        iPokemonList = retrofit.create(PokemonService::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        val itemView = binding.root

        pokemonRecyclerView = binding.pokemonRecyclerview

        pokemonRecyclerView.setHasFixedSize(true)
        pokemonRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        val itemDecoration = ItemOffsetDecoration(requireActivity(), R.dimen.spacing)
        pokemonRecyclerView.addItemDecoration(itemDecoration)

        fetchData()

        return itemView
    }

    private fun fetchData() {
        compositeDisposable.add(
            iPokemonList.listPokemon
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ pokemonDex ->
                    Common.pokemonList = pokemonDex.pokemon ?: emptyList()
                    val adapter = PokemonListAdapter(requireActivity(), Common.pokemonList)
                    pokemonRecyclerView.adapter = adapter
                }, { error ->
                    error.printStackTrace()
                    // Обработка ошибок при получении данных
                })
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}

