package com.example.quickmart.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash")
    object Login : Screen("login")
    object Otp : Screen("otp")
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Checkout : Screen("checkout")
    object Success : Screen("success")

    object Categories : Screen("categories")
    object Profile : Screen("profile")
}