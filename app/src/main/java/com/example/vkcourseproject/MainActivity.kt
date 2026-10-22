package com.example.vkcourseproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vkcourseproject.ui.theme.VKCourseProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKCourseProjectTheme {
                AppNavigation()
            }
        }
    }
    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "list") {
            composable("list") {
                Scaffold(
                    topBar = { AppListToolbar() }
                ) { innerPadding ->
                    AppListScreen(
                        apps = sampleApps,
                        onAppClick = { app ->
                            navController.navigate("details/${app.id}")
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

            composable("details/{appId}") { backStackEntry ->
                val appId = backStackEntry.arguments?.getString("appId")?.toIntOrNull()
                val app = sampleApps.find { it.id == appId } ?: sampleApps.first()

                AppDetailsScreen(
                    app = app,
                    onBackClick = { navController.popBackStack() },
                    onShowcaseAppClick = { clickedApp ->
                        navController.navigate("details/${clickedApp.id}") {
                            popUpTo("details/{appId}") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}


