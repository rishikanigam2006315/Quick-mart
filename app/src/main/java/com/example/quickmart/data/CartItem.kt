package com.example.quickmart.data

data class CartItem(

    val productId: Int,
    val name: String,
    val image: String,
    val price: Int,
    var quantity: Int = 1
)