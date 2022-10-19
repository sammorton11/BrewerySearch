package com.samm.brewerysearch.presentation.screens.main.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samm.brewerysearch.R
import com.samm.brewerysearch.presentation.navigation.Screens
import com.samm.brewerysearch.presentation.ui.theme.Gray50
import com.samm.brewerysearch.presentation.ui.theme.LightGreen

@Composable
fun BrewTopBar(navController: NavController, search: String?) {
    TopAppBar(
        modifier = Modifier
            .height(55.dp)
            .fillMaxWidth(),
        title = {
            Text(
                stringResource(id = R.string.main_title),
                style = MaterialTheme.typography.h5,
                maxLines = 1
            )
        },
        actions = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$search",
                    color = LightGreen
                )
                IconButton(onClick = { navController.navigate(Screens.SearchScreen.name) }) {
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.search)
                    )
                }
            }

        },
        backgroundColor = Gray50
    )
}