package com.samm.brewerysearch.presentation.screens.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SortingMenu(sortByName: Boolean, sortByCity: Boolean, sortByAddress: Boolean,) {

    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Name", "City", "Address")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        Text(
            text = "Sort by: ${items[selectedIndex]}",
            modifier = Modifier
                .clickable(onClick = { expanded = true })
                .width(120.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false // todo: sort list data when clicked
                when(selectedIndex){
                    0 -> sortByName == true
                    1 -> sortByCity == true
                    2 -> sortByAddress == true
                } }
        ) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    val disabledText = if (text == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = text + disabledText)
                }
            }
        }
    }
}
