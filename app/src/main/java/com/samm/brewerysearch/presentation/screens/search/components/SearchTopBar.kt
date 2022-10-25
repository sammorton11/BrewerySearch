package com.samm.brewerysearch.presentation.screens.search.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.samm.brewerysearch.R

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

                backgroundColor = Color.Gray
            )
        },

        content = { SearchScreenContent(navController = navController) }
    )
}

