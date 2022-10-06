package com.samm.brewerysearch.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.samm.brewerysearch.R
import com.samm.brewerysearch.presentation.navigation.ScreenNavigation
import com.samm.brewerysearch.presentation.ui.theme.BreweryTheme
import dagger.hilt.android.AndroidEntryPoint


/*
TODO: 
 - Improve UI: Colors ; Text fonts/styles
 - Add search results amount to main screen - toast message - text label
 - Feature to sort list
 - Click card to select options: Directions, Website, Add to Favorites
 - Database
 - Tests
*/

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreweryTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { Column(modifier = Modifier.background(colorResource(id = R.color.grey_blue))) {
                        ScreenNavigation()
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BreweryTheme {
        ScreenNavigation()
    }
}