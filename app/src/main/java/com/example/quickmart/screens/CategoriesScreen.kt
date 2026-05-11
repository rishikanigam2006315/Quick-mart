package com.example.quickmart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quickmart.components.BottomBar

data class CategoryItem(
    val title: String,
    val emoji: String
)

@Composable
fun CategoriesScreen(
    navController: NavController
) {

    val categories = listOf(
        CategoryItem("Fruits", "🍎"),
        CategoryItem("Vegetables", "🥦"),
        CategoryItem("Dairy", "🥛"),
        CategoryItem("Bakery", "🍞"),
        CategoryItem("Beverages", "🥤"),
        CategoryItem("Snacks", "🍫"),
        CategoryItem("Personal Care", "🧴"),
        CategoryItem("Household", "🏠")
    )

    Scaffold(

        bottomBar = {
            BottomBar(navController)
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Shop by Category",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Find products easily",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyVerticalGrid(

                columns = GridCells.Fixed(2),

                verticalArrangement = Arrangement.spacedBy(16.dp),

                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(categories) { category ->

                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .clickable {

                                navController.navigate(
                                    "category_products/${category.title}"
                                )
                            },

                        shape = RoundedCornerShape(28.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {

                        Column(

                            modifier = Modifier
                                .fillMaxSize()
                                .padding(18.dp),

                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = category.emoji,
                                fontSize = 42.sp
                            )

                            Column {

                                Text(
                                    text = category.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )

                                Text(
                                    text = "Explore now",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}