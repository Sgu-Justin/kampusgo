package com.example.kampusgo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun KampusApp(eventsContent: @Composable () -> Unit) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: "events"

    Scaffold(
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    TextButton(
                        onClick = { navController.popBackStack("events", inclusive = false) },
                        enabled = currentRoute != "events",
                    ) {
                        Text("Events")
                    }

                    TextButton(
                        onClick = {
                            navController.navigate("notes") {
                                launchSingleTop = true
                            }
                        },
                        enabled = currentRoute != "notes",
                    ) {
                        Text("Notes")
                    }
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "events",
            modifier = Modifier.padding(innerPadding),
        ) {
            composable("events") { eventsContent() }
            composable("notes") { NotesScreen() }
        }
    }
}