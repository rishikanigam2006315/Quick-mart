package com.example.quickmart.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quickmart.screens.AddressScreen
import com.example.quickmart.screens.CartScreen
import com.example.quickmart.screens.CategoriesScreen
import com.example.quickmart.screens.CategoryProductsScreen
import com.example.quickmart.screens.CheckoutScreen
import com.example.quickmart.screens.HomeScreen
import com.example.quickmart.screens.LoginScreen
import com.example.quickmart.screens.MyOrdersScreen
import com.example.quickmart.screens.OtpScreen
import com.example.quickmart.screens.PaymentScreen
import com.example.quickmart.screens.PrivacyScreen
import com.example.quickmart.screens.ProfileScreen
import com.example.quickmart.screens.SettingsScreen
import com.example.quickmart.screens.SplashScreen
import com.example.quickmart.screens.SuccessScreen
import com.example.quickmart.screens.SupportScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(Screen.Otp.route) {
            OtpScreen(navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Cart.route) {
            CartScreen(navController)
        }

        composable(Screen.Checkout.route) {
            CheckoutScreen(navController)
        }

        composable(Screen.Success.route) {
            SuccessScreen(navController)
        }

        composable(Screen.Categories.route) {
            CategoriesScreen(navController)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        composable(
            route = "category_products/{category}"
        ) {

            val category =
                it.arguments?.getString("category") ?: ""

            CategoryProductsScreen(
                navController = navController,
                category = category
            )
        }
        composable("orders") {
            MyOrdersScreen(navController)
        }

        composable("address") {
            AddressScreen(navController)
        }

        composable("payment") {
            PaymentScreen(navController)
        }

        composable("support") {
            SupportScreen(navController)
        }

        composable("settings") {
            SettingsScreen(navController)
        }
        composable("privacy") {
            PrivacyScreen(navController)
        }
    }
}