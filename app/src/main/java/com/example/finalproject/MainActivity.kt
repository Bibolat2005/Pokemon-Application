package com.example.finalproject

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.finalproject.common.Common
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val showDetail = object:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
                if(intent!!.action!!.toString() == Common.KEY_ENABLE_HOME){
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)

                        val detailFragment = PokemonDetail.getInstance()
                        val position = intent.getIntExtra("position", -1)
                        val bundle = Bundle()
                        bundle.putInt("position", position)
                        detailFragment.arguments = bundle

                        val fragmentTransaction = supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.list_pokemon_fragment,detailFragment)
                        fragmentTransaction.addToBackStack("detail")
                        fragmentTransaction.commit()
                }

        }
    }

    private val showEvolution = object: BroadcastReceiver() {
        @SuppressLint("ResourceType")
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent!!.action!!.toString() == Common.KEY_NUM_EVOLUTION) {

                    val detailFragment = PokemonDetail.getInstance()
                    val bundle = Bundle()
                    val num = intent.getStringExtra("num")
                    bundle.putString("num", num)
                    detailFragment.arguments = bundle

                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.list_pokemon_fragment, detailFragment)
                    fragmentTransaction.addToBackStack("detail")
                    fragmentTransaction.commit()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showDetail, IntentFilter(Common.KEY_ENABLE_HOME))
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showEvolution, IntentFilter(Common.KEY_NUM_EVOLUTION))
    }

}
