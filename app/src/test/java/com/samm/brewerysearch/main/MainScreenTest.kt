package com.samm.brewerysearch.main

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.samm.brewerysearch.presentation.screens.main.MainScreen
import com.samm.brewerysearch.presentation.ui.theme.BreweryTheme
import com.samm.brewerysearch.util.Constants
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@HiltAndroidTest // 1
@Config(application = HiltTestApplication::class) // 2
@RunWith(RobolectricTestRunner::class) // 3
@LooperMode(LooperMode.Mode.PAUSED)
class MainScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
//        hiltRule.inject()

        composeRule.setContent {
            val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            BreweryTheme {
                MainScreen(
                    navController = navController,
                    mainViewModel = hiltViewModel(),
                    search = Constants.DEFAULT_CITY
                )
            }
        }
    }


    @Test
    fun my_Test(){
        composeRule.onNodeWithText("Breweries").assertIsDisplayed()
    }
}