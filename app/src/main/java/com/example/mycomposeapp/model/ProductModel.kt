package com.example.mycomposeapp.model

data class ProductModel(
    var image: String,
    var name: String,
    val price: Double,
    val featured: Boolean,
    val location: String,
    val visitors: Int
)