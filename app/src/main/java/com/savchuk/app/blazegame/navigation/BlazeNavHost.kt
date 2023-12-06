package com.savchuk.app.blazegame.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.savchuk.app.blazegame.game.ui.GameScreen
import com.savchuk.app.blazegame.menu.MenuScreen
import com.savchuk.app.blazegame.splash.SplashScreen
import com.savchuk.app.blazegame.web.WebScreen

@Composable
fun BlazeNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Splash.route ){
        composable(route = Screens.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screens.Menu.route){
            MenuScreen(navController = navController)
        }
        composable(route = Screens.Game.route){
            GameScreen(navController = navController)
        }
        composable(route = Screens.Web.route){
            WebScreen()
        }
    }

}

sealed class Screens(val route: String){

    object Splash: Screens("Splash")
    object Game: Screens("Game")
    object Menu: Screens("Menu")
    object Web: Screens("Web")

}