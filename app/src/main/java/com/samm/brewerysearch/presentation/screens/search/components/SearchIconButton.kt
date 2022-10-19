package com.samm.brewerysearch.presentation.screens.search.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samm.brewerysearch.presentation.navigation.Screens

@Composable
fun SearchIconButton(textState: String, navController: NavController){
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
            imageVector = Icons.Filled.Search,
            contentDescription = "",
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}