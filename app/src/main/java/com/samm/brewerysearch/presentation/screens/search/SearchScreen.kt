package com.samm.brewerysearch.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.samm.brewerysearch.presentation.ui.theme.BreweryTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController){
    BreweryTheme {
        SearchScreenTopAppBar(navController = navController)
    }
}

