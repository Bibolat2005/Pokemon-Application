package com.example.finalproject.model.entity

data class Pokemon(
    var id: Int=0,
    var num: String?,
    var name: String?,
    var img: String?,
    var type: List<String>?,
    var height: String?,
    var weight: String?,
    var candy:String?,
    var candy_count: Int=0,
    var egg: String?,
    var spawn_chance: Double=0.toDouble(),
    var spawn_time:String?,
    var weaknesses: List<String>?,
    var next_evolution: List<Evolution>?,
    var prev_evolution: List<Evolution>?
)
