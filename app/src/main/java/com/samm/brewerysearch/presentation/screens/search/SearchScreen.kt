package com.samm.brewerysearch.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.samm.brewerysearch.R
import com.samm.brewerysearch.presentation.navigation.Screens
import com.samm.brewerysearch.util.Constants


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController){
    SearchScreenTopAppBar(navController = navController)
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreenTopAppBar(navController: NavController){

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxSize(),
                title = {
                    Text(
                        stringResource(id = R.string.search_screen_title),
                        fontSize = 15.sp,
                        maxLines = 1
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ){
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back to Main"
                        )
                    }
                },
                backgroundColor = colorResource(id = R.color.light_blue)
            )
        },
        
        content = { SearchScreenContent(navController = navController) }
    )
}


@Composable
fun SearchScreenContent(navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey_blue))
    ) {

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
                label = { Text(stringResource(id = R.string.search)) },
                placeholder = {Constants.DEFAULT_CITY}
            )

            //Todo: maybe change this to a regular "Submit" button
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
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "")
            }

        }

    }
}