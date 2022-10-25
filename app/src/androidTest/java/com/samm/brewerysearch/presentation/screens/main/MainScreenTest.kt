package com.samm.brewerysearch.presentation.screens.main

import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.samm.brewerysearch.R
import com.samm.brewerysearch.activity.MainActivity
import com.samm.brewerysearch.di.AppModule
import com.samm.brewerysearch.presentation.navigation.ScreenNavigation
import com.samm.brewerysearch.presentation.screens.main.components.brewData
import com.samm.brewerysearch.presentation.ui.theme.BreweryTheme
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
        composeRule.activity.apply {
            setContent {
                BreweryTheme {
                    Surface {
                        val navController = rememberNavController()
                        Column(modifier = Modifier.background(Color.DarkGray)) {
                            MainScreen(
                                navController = navController,
                                mainViewModel = hiltViewModel(),
                                search = "Tulsa"
                            )
                        }
                    }
                }
            }
        }
    }


    @Test
    fun myTest(){

        composeRule.onNodeWithText("Breweries").assertExists()
        composeRule.onNodeWithText("Tulsa").assertExists()

    }
}