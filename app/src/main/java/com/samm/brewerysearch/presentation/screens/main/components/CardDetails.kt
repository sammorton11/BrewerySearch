package com.samm.brewerysearch.presentation.screens.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardDetails(
    city: String?,
    state: String?,
    street: String?,
    country: String?,
    county: String?,
    postalCode: String?,
    breweryType: String?,
    lastUpdated: String?,
    expanded: Boolean
){
    // Third Row
    //Brewery Details
    Column(
        modifier = Modifier.padding(
            start = 30.dp,
            end = 10.dp,
            top = 25.dp,
            bottom = 15.dp
        ),
        verticalArrangement = Arrangement.Center
    ) {
        if (expanded) {
            Text(text = "City:  $city")
            Text(text = "State:  $state")
            Text(text = "Street:  $street")
            Text(text = "Country:  $country")
            Text(text = "County:  $county")
            Text(text = "Postal Code:  $postalCode")
            Text(text = "Type:  $breweryType")
            Text(text = "Last updated:  $lastUpdated")
        }
    }
}