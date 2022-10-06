package com.samm.brewerysearch.presentation.screens.main.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samm.brewerysearch.R
import com.samm.brewerysearch.data.models.BrewData
import com.samm.brewerysearch.presentation.screens.main.MainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BreweryCard(
    bData: List<BrewData>,
    position: Int,
    viewModel: MainViewModel
){
    val cardNumber = position+1
    val cityApiData = bData[position].city
    val phoneNumberApiData = bData[position].phone
    val countryApiData = bData[position].country
    val breweryTypeApiData = bData[position].brewery_type
    val countyApiData = bData[position].county_province
    val postalCodeApiData = bData[position].postal_code
    val stateApiData = bData[position].state
    val streetApiData = bData[position].street
    val apiLastUpdated = bData[position].updated_at

    val context = LocalContext.current
    val lastUpdated = apiLastUpdated?.let { viewModel.dateTextConverter(it) }
    val websiteUrlApiData = bData[position].website_url
    var expanded by remember { mutableStateOf(false) }

    val clickableWebsiteText = buildAnnotatedString {
        if (websiteUrlApiData != null) {
            append(websiteUrlApiData)
        }
    }
    val clickablePhoneNumberText = buildAnnotatedString {
        if (phoneNumberApiData != null){
            append(phoneNumberApiData)
        }
    }

        //Brewery Card
        Card(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                // .fillMaxSize()
                .clickable(
                    enabled = true,
                    onClickLabel = "Expand to view details",
                    onClick = { expanded = !expanded }
                )
                .semantics { contentDescription = "Brewery Card" },
            backgroundColor = colorResource(id = R.color.light_blue),
            contentColor = Color.Black,
            border = BorderStroke(0.5.dp, colorResource(id = R.color.pink)),
            elevation = 15.dp

        ) {

            Column(verticalArrangement = Arrangement.Center) {

                //Number text for position of card
                Text(
                    text = cardNumber.toString(),
                    modifier = Modifier.padding(15.dp),
                    fontSize = 10.sp,
                )

                BreweryTitle(
                    bData = bData,
                    position = position
                )

                CardDetails(
                    city = cityApiData,
                    state = stateApiData,
                    street = streetApiData,
                    country = countryApiData,
                    county = countyApiData,
                    postalCode = postalCodeApiData,
                    breweryType = breweryTypeApiData,
                    lastUpdated = lastUpdated,
                    expanded = expanded
                )

                CardLinks(
                    phoneNumberText = clickablePhoneNumberText,
                    phoneNumberData = phoneNumberApiData,
                    websiteText = clickableWebsiteText,
                    websiteUrlData = websiteUrlApiData,
                    viewModel = viewModel,
                    context = context
                )
            }
        }
    }