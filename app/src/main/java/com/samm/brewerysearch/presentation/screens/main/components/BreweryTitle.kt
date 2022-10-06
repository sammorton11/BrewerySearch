package com.samm.brewerysearch.presentation.screens.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samm.brewerysearch.R
import com.samm.brewerysearch.data.models.BrewData

@Composable
fun BreweryTitle(bData: List<BrewData>, position: Int){
    // Second Row

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            // Name of Brewery
            Text(
                text = bData[position].name!!,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                textAlign = TextAlign.Center,
                softWrap = true,
                style = TextStyle(
                    color = colorResource(id = R.color.purple_500),
                    fontStyle = FontStyle.Normal,
                    fontSize = 17.sp,
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 2.sp,
                )

            )
        }
    }

}