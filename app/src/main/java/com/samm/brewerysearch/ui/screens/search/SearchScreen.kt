package com.samm.brewerysearch.ui.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samm.brewerysearch.ui.navigation.Screens
import com.samm.brewerysearch.util.Constants


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController){

    SearchAppBar(navController = navController)

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchAppBar(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(),

                title = {
                    Text(
                        "Search for a Brewery",
                        maxLines = 1
                    )
                },

                navigationIcon = {

                    IconButton(onClick = { navController.popBackStack() }){
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back to Main"
                        )
                    }
                }
            )
        },
        
        content = { 
            SearchContent(navController = navController)
        }
    )
}


@Composable
fun SearchContent(navController: NavController){

    Column {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            var textState by remember { mutableStateOf("") }

            OutlinedTextField(
                value = textState,
                onValueChange = { textState = it },
                label = { Text("Search") },
                placeholder = {Constants.DEFAULT_CITY}
            )

            IconButton(
                onClick = {
                    if (textState.isBlank()){
                        return@IconButton
                    }
                    else{
                        navController.navigate(Screens.MainScreen.name + "/${textState}")
                    }

                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }

        }

    }
}