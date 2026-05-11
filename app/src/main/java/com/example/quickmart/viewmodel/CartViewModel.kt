package com.example.quickmart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickmart.data.CartItem
import com.example.quickmart.data.Order
import com.example.quickmart.repository.QuickMartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: QuickMartRepository
) : ViewModel() {

    val cartItems: StateFlow<List<CartItem>> =
        repository.cartItems
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                emptyList()
            )

    val cartTotal: StateFlow<Int> =
        repository.cartItems
            .map { items ->

                items.sumOf {
                    it.price * it.quantity
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                0
            )

//    private val _orders =
//        MutableStateFlow<List<Order>>(emptyList())
//
//    val orders: StateFlow<List<Order>> = _orders

    val orders: StateFlow<List<Order>> =
        repository.orders.stateIn(

            viewModelScope,

            SharingStarted.Lazily,

            emptyList()
        )

    fun addToCart(
        productId: Int,
        currentQuantity: Int = 0
    ) {

        viewModelScope.launch {

            repository.updateCartItem(
                productId,
                currentQuantity + 1
            )
        }
    }

    fun removeFromCart(
        productId: Int,
        currentQuantity: Int
    ) {

        viewModelScope.launch {

            repository.updateCartItem(
                productId,
                currentQuantity - 1
            )
        }
    }

    fun deleteFromCart(
        productId: Int
    ) {

        viewModelScope.launch {

            repository.removeFromCart(productId)
        }
    }

    fun checkout() {

        viewModelScope.launch {

            repository.placeOrder(

                cartItems.value,

                cartTotal.value
            )
        }
    }
//    fun checkout() {
//
//        viewModelScope.launch {
//
//            repository.placeOrder(
//
//                cartItems.value,
//
//                cartTotal.value
//            )
//
//            _orders.value =
//                repository.getOrders()
//        }
//    }
}