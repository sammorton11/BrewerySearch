package com.samm.brewerysearch.presentation.screens.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samm.brewerysearch.R
import com.samm.brewerysearch.util.Constants

@Composable
fun SearchFieldAndButton(navController: NavController, colors: TextFieldColors){

    var textState by remember { mutableStateOf("") }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(
            value = textState,
            onValueChange = {textState = it},
            modifier = Modifier.padding(start = 15.dp),
            enabled = true,
            label = {
                Text(
                    stringResource(id = R.string.search),
                    color = Color.White
                )
            },
            placeholder = { Constants.DEFAULT_CITY},
            colors = colors

        )

        SearchIconButton(textState, navController)

    }
}