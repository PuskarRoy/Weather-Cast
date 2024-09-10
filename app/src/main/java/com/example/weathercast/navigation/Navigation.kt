package com.example.weathercast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weathercast.screens.AboutScreen
import com.example.weathercast.screens.FavouriteScreen
import com.example.weathercast.screens.MainScreen
import com.example.weathercast.screens.SearchScreen
import com.example.weathercast.screens.SettingScreen
import com.example.weathercast.screens.SplashScreen
import com.example.weathercast.viewmodel.AppViewModel

@Composable
fun MyAppNavs() {
    val navHost = rememberNavController()

    val viewModel: AppViewModel = hiltViewModel()

    NavHost(navController = navHost, startDestination = AppScreen.SplashScreen.route) {

        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navHost)
        }

        composable(AppScreen.MainScreen.route) {
            MainScreen(navHost, viewModel)
        }

        composable(AppScreen.SearchScreen.route) {
            SearchScreen(navHost, viewModel)
        }

        composable(AppScreen.SettingScreen.route) {
            SettingScreen(navHost,viewModel)
        }

        composable(AppScreen.AboutScreen.route) {
            AboutScreen(navHost)
        }
        composable(AppScreen.FavoriteScreen.route) {
            FavouriteScreen(navHost,viewModel)
        }


    }
}