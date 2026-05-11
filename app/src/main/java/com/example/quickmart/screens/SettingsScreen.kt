package com.example.quickmart.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.example.quickmart.utils.ThemeManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val themeManager = remember { ThemeManager.getInstance(context) }
    val darkModeEnabled by themeManager.isDarkMode.collectAsState()

    var notificationsEnabled by remember {
        mutableStateOf(true)
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("Settings")
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {

                Row(
                    modifier = Modifier.padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row {

                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "Notifications",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Switch(
                        checked = notificationsEnabled,
                        onCheckedChange = {
                            notificationsEnabled = it
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {

                Row(
                    modifier = Modifier.padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row {

                        Icon(
                            imageVector = Icons.Default.DarkMode,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "Dark Mode",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Switch(
                        checked = darkModeEnabled,
                        onCheckedChange = {
                            themeManager.setDarkMode(it)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(

                onClick = {
                    navController.navigate("privacy")
                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(20.dp)
            ) {

                Row(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Privacy & Security",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(20.dp)
//            ) {
//
//                Row(
//                    modifier = Modifier.padding(20.dp)
//                ) {
//
//                    Icon(
//                        imageVector = Icons.Default.Security,
//                        contentDescription = null
//                    )
//
//                    Spacer(modifier = Modifier.width(12.dp))
//
//                    Text(
//                        text = "Privacy & Security",
//                        fontWeight = FontWeight.Bold
//                    )
//                }
//            }
        }
    }
}