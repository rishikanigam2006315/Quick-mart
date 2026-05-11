package com.example.quickmart.repository

import com.example.quickmart.data.CartItem
import com.example.quickmart.data.Order
import com.example.quickmart.data.Product
import com.example.quickmart.data.local.AppDao
import com.example.quickmart.data.local.entity.CartItemEntity
import com.example.quickmart.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class QuickMartRepository(
    private val appDao: AppDao
) {
    private fun ProductEntity.toDomainModel() = Product(
        id = id,
        name = name,
        weight = weight,
        price = price,
        image = image
    )
    private fun Product.toEntityModel() = ProductEntity(
        id = id,
        name = name,
        weight = weight,
        price = price,
        image = image
    )
    val products: Flow<List<Product>> =
        appDao.getAllProducts().map { entities ->

            entities.map {
                it.toDomainModel()
            }
        }
    val cartItems: Flow<List<CartItem>> = combine(

        appDao.getAllCartItems(),
        appDao.getAllProducts()

    ) { cartEntities, productEntities ->

        cartEntities.mapNotNull { cartEntity ->

            val productEntity = productEntities.find {
                it.id == cartEntity.productId
            }

            if (productEntity != null) {

                CartItem(
                    productId = productEntity.id,
                    name = productEntity.name,
                    image = productEntity.image,
                    price = productEntity.price,
                    quantity = cartEntity.quantity
                )

            } else {
                null
            }
        }
    }
    private val _orders =
        kotlinx.coroutines.flow.MutableStateFlow<List<Order>>(emptyList())

    val orders =
        _orders
    suspend fun placeOrder(

        cartItems: List<CartItem>,
        total: Int
    ) {

        val currentOrders = _orders.value.toMutableList()

        val order = Order(

            id = currentOrders.size + 1,

            items = cartItems,

            total = total,

            status = "Confirmed"
        )

        currentOrders.add(order)

        _orders.value = currentOrders

        clearCart()
    }
    suspend fun insertInitialProducts(
        products: List<Product>
    ) {

        appDao.insertProducts(

            products.map {
                it.toEntityModel()
            }
        )
    }
    suspend fun addToCart(
        product: Product
    ) {

        val existingItem = appDao.getCartItem(product.id)

        if (existingItem != null) {

            appDao.insertCartItem(
                CartItemEntity(
                    productId = product.id,
                    quantity = existingItem.quantity + 1
                )
            )

        } else {

            appDao.insertCartItem(
                CartItemEntity(
                    productId = product.id,
                    quantity = 1
                )
            )
        }
    }

    suspend fun updateCartItem(
        productId: Int,
        quantity: Int
    ) {

        if (quantity <= 0) {

            appDao.deleteCartItem(productId)

        } else {

            appDao.insertCartItem(
                CartItemEntity(
                    productId = productId,
                    quantity = quantity
                )
            )
        }
    }
    suspend fun removeFromCart(
        productId: Int
    ) {

        appDao.deleteCartItem(productId)
    }
    suspend fun clearCart() {

        appDao.clearCart()
    }
}