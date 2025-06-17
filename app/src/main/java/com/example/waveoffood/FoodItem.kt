package com.example.waveoffood

data class FoodItem(
    val name: String,
    val description: String,
    val price: String,
    val imageResourceId: Int,
    var quantity: Int = 1
)
