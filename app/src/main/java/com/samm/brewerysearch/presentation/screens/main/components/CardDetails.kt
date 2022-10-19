package com.samm.brewerysearch.presentation.screens.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

    val fontSize = 18.sp

    var countyText = "Not Available"
    if (county != null){
        countyText = county
    }

    // Third Row
    //Brewery Details
    Column(
        modifier = Modifier.padding(
            start = 30.dp,
            end = 10.dp,
            top = 30.dp,
            bottom = 15.dp
        ),
        verticalArrangement = Arrangement.Center
    ) {
        if (expanded) {

            Text(
                text = "$city",
                color = Color.DarkGray,
                fontSize = fontSize
            )
            Text(
                text = "$state",
                color = Color.DarkGray,
                fontSize = fontSize
            )
            Text(
                text = "$street",
                color = Color.DarkGray,
                fontSize = fontSize
            )
            Text(
                text = "$country",
                color = Color.DarkGray,
                fontSize = fontSize
            )
            Text(text = "$countyText",
                color = Color.DarkGray,
                fontSize = fontSize
            )
            Text(
                text = "$postalCode",
                color = Color.DarkGray,
                fontSize = fontSize
            )
            Text(
                text = "$breweryType",
                color = Color.DarkGray,
                fontSize = fontSize
            )
            Text(
                text = "$lastUpdated",
                color = Color.DarkGray,
                fontSize = fontSize
            )
        }
    }
}


@Preview
@Composable
fun previewCard(){
    CardDetails(
        city = "Tulsa",
        state = "Oklahoma",
        street = "Street Name",
        country = "USA",
        county = "county",
        postalCode = "73034",
        breweryType = "micro",
        lastUpdated = "date",
        expanded = true
    )
}