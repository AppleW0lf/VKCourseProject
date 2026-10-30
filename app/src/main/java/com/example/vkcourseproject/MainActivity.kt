package com.example.vkcourseproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vkcourseproject.presentation.AppDetailsScreen
import com.example.vkcourseproject.presentation.AppListScreen
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

                AppListScreen(
                    onAppClick = { app ->
                        navController.navigate("details/${app.id}")
                    }
                )
            }

            composable("details/{appId}") { backStackEntry ->
                val appId = backStackEntry.arguments?.getString("appId")?.toIntOrNull() ?: 0
                AppDetailsScreen(
                    appId = appId,
                    onBackClick = { navController.popBackStack() },
                    onShowcaseAppClick = { clickedApp ->
                        navController.navigate("details/${clickedApp.id}")
                    }
                )
            }
        }
    }
}


