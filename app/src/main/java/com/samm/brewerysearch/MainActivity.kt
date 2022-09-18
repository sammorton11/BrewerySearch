package com.samm.brewerysearch

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.samm.brewerysearch.ui.navigation.ScreenNavigation
import com.samm.brewerysearch.ui.theme.BloombergTheme
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
            BloombergTheme (darkTheme = true){
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
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
    BloombergTheme {
        ScreenNavigation()
    }
}