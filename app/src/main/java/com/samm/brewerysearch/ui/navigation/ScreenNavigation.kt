package com.samm.brewerysearch.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.samm.brewerysearch.ui.screens.main.MainScreen
import com.samm.brewerysearch.ui.screens.main.MainViewModel
import com.samm.brewerysearch.ui.screens.search.SearchScreen
import com.samm.brewerysearch.ui.screens.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenNavigation() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Screens.SplashScreen.name) {

        val route = Screens.MainScreen.name
        composable("$route/{city}",
            listOf(navArgument("city"){type = NavType.StringType})
        ) { navBack ->
            navBack.arguments?.getString("city").let{ city ->
                val viewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController, viewModel, city)
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