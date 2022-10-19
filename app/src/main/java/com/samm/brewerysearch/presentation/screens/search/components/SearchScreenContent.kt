package com.samm.brewerysearch.presentation.screens.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.samm.brewerysearch.presentation.screens.search.util.MyAppTextFieldColors

@Composable
fun SearchScreenContent(navController: NavController){

    val colors: TextFieldColors = MyAppTextFieldColors()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        SearchFieldAndButton(navController, colors)
    }
}


