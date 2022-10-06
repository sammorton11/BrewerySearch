package com.samm.brewerysearch.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.samm.brewerysearch.presentation.screens.main.MainScreen
import com.samm.brewerysearch.presentation.screens.main.MainViewModel
import com.samm.brewerysearch.presentation.screens.search.SearchScreen
import com.samm.brewerysearch.presentation.screens.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenNavigation() {

    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()

    NavHost(navController, startDestination = Screens.SplashScreen.name) {

        val route = Screens.MainScreen.name
        composable("$route/{search}",
            listOf(navArgument("search"){type = NavType.StringType})
        ) { navBack ->
            navBack.arguments?.getString("search").let{ search ->
                MainScreen(navController, mainViewModel, search)
            }
        }

        composable(Screens.SearchScreen.name) {
            SearchScreen(navController)
        }
        
        composable(Screens.SplashScreen.name){
            SplashScreen(navController = navController)
        }

    }
}