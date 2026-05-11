package com.example.quickmart.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.quickmart.data.Product
import com.example.quickmart.viewmodel.AppViewModelProvider
import com.example.quickmart.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryProductsScreen(

    navController: NavController,
    category: String,

    viewModel: HomeViewModel =
        viewModel(factory = AppViewModelProvider.Factory)
) {

    val products = when (category) {

        "Fruits" -> listOf(

            Product(
                1,
                "Fresh Apples",
                "1kg",
                120,
                "https://images.unsplash.com/photo-1567306226416-28f0efdc88ce"
            ),

            Product(
                2,
                "Bananas",
                "1 dozen",
                60,
                "https://images.unsplash.com/photo-1574226516831-e1dff420e37f"
            )
        )

        "Vegetables" -> listOf(

            Product(
                3,
                "Tomatoes",
                "1kg",
                40,
                "https://images.unsplash.com/photo-1546094096-0df4bcaaa337"
            ),

            Product(
                4,
                "Potatoes",
                "2kg",
                70,
                "https://images.unsplash.com/photo-1518977676601-b53f82aba655"
            )
        )

        "Dairy" -> listOf(

            Product(
                5,
                "Milk",
                "1L",
                30,
                "https://images.unsplash.com/photo-1550583724-b2692b85b150"
            ),

            Product(
                6,
                "Cheese",
                "200g",
                90,
                "https://images.unsplash.com/photo-1486297678162-eb2a19b0a32d"
            )
        )

        "Bakery" -> listOf(

            Product(
                7,
                "Bread",
                "400g",
                45,
                "https://images.unsplash.com/photo-1509440159596-0249088772ff"
            ),

            Product(
                8,
                "Cake",
                "500g",
                250,
                "https://images.unsplash.com/photo-1578985545062-69928b1d9587"
            )
        )

        "Beverages" -> listOf(

            Product(
                9,
                "Coca Cola",
                "750ml",
                40,
                "https://images.unsplash.com/photo-1629203851122-3726ecdf080e"
            ),

            Product(
                10,
                "Orange Juice",
                "1L",
                120,
                "https://images.unsplash.com/photo-1600271886742-f049cd451bba"
            )
        )

        "Snacks" -> listOf(

            Product(
                11,
                "Chips",
                "100g",
                20,
                "https://images.unsplash.com/photo-1566478989037-eec170784d0b"
            ),

            Product(
                12,
                "Cookies",
                "250g",
                80,
                "https://images.unsplash.com/photo-1499636136210-6f4ee915583e"
            )
        )

        "Personal Care" -> listOf(

            Product(
                13,
                "Shampoo",
                "500ml",
                220,
                "https://images.unsplash.com/photo-1526947425960-945c6e72858f"
            ),

            Product(
                14,
                "Soap",
                "Pack of 4",
                140,
                "https://images.unsplash.com/photo-1607006483225-2f7a7f1b4f45"
            )
        )

        else -> listOf(

            Product(
                15,
                "Detergent",
                "1kg",
                180,
                "https://images.unsplash.com/photo-1583947215259-38e31be8751f"
            ),

            Product(
                16,
                "Floor Cleaner",
                "1L",
                160,
                "https://images.unsplash.com/photo-1581578731548-c64695cc6952"
            )
        )
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(category)
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }

    ) { padding ->

        LazyVerticalGrid(

            columns = GridCells.Fixed(2),

            modifier = Modifier
                .fillMaxSize()
                .padding(padding),

            contentPadding = PaddingValues(12.dp)
        ) {

            items(products) { product ->

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),

                    shape = RoundedCornerShape(20.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        AsyncImage(
                            model = product.image,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = product.name,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(product.weight)

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "₹${product.price}",
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = {

                                viewModel.addToCart(product)
                            },

                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text("Add")
                        }
                    }
                }
            }
        }
    }
}