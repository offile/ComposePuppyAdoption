package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.androiddevchallenge.ui.detail.DetailScreen
import com.example.androiddevchallenge.ui.home.HomeScreen

@Composable
fun PuppyAdoptionApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id"){ type = NavType.StringType })
        ) {
            DetailScreen(
                id = it.arguments!!.getString("id")!!,
                navController = navController
            )
        }
    }
}