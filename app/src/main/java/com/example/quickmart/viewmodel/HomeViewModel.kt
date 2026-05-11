package com.example.quickmart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickmart.data.Product
import com.example.quickmart.data.products
import com.example.quickmart.repository.QuickMartRepository
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: QuickMartRepository
) : ViewModel() {

    val products: StateFlow<List<Product>> = repository.products
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        // Pre-populate dummy data if db is empty
        viewModelScope.launch {
            repository.insertInitialProducts(com.example.quickmart.data.products)
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            repository.addToCart(product)
        }
    }
}
