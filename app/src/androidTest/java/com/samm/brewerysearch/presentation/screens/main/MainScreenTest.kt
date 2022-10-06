package com.samm.brewerysearch.presentation.screens.main

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samm.brewerysearch.activity.MainActivity
import com.samm.brewerysearch.domain.di.AppModule
import com.samm.brewerysearch.presentation.navigation.Screens
import com.samm.brewerysearch.presentation.ui.theme.BreweryTheme
import com.samm.brewerysearch.util.Constants
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class MainScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.setContent {
            val navController = rememberNavController()
            BreweryTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screens.MainScreen.name
                ){
                    composable(Screens.MainScreen.name){
                        MainScreen(
                            navController = navController,
                            mainViewModel = hiltViewModel(),
                            search = Constants.DEFAULT_CITY
                        )
                    }
                }
            }
        }
    }


    @Test
    fun myTest(){
        composeRule.onNodeWithText("Breweries").assertIsDisplayed()
    }
}