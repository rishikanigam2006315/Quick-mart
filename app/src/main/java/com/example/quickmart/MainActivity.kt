package com.example.quickmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.quickmart.navigation.AppNavGraph
import com.example.quickmart.ui.theme.QuickMartTheme

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.quickmart.utils.ThemeManager
import androidx.compose.foundation.isSystemInDarkTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val themeManager = ThemeManager.getInstance(this)

        setContent {
            val isDarkMode by themeManager.isDarkMode.collectAsState()
            
            QuickMartTheme(darkTheme = isDarkMode) {

                val navController = rememberNavController()

                AppNavGraph(navController)
            }
        }
    }
}