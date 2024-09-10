package com.example.weathercast.navigation

sealed class AppScreen (val route: String){
    data object MainScreen:AppScreen("main screen")
    data object SettingScreen:AppScreen("setting screen")
    data object SearchScreen:AppScreen("search screen")
    data object AboutScreen:AppScreen("about screen")
    data object SplashScreen:AppScreen("splash screen")
    data object FavoriteScreen:AppScreen("favorite screen")
}