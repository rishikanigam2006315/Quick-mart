package com.example.quickmart.data

data class Order(

    val id: Int,
    val items: List<CartItem>,
    val total: Int,
    val status: String
)