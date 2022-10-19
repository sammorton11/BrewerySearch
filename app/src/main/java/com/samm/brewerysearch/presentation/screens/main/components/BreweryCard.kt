package com.samm.brewerysearch.presentation.screens.main.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samm.brewerysearch.domain.models.BrewData
import com.samm.brewerysearch.presentation.screens.main.MainViewModel
import com.samm.brewerysearch.presentation.screens.util.makeLink

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BreweryCard(
    bData: List<BrewData>,
    position: Int,
    viewModel: MainViewModel,
    color: Color
){

    val context = LocalContext.current
    val cardNumber = position+1
    val cityApiData = bData[position].city
    val countryApiData = bData[position].country
    val breweryTypeApiData = bData[position].brewery_type
    val countyApiData = bData[position].county_province
    val postalCodeApiData = bData[position].postal_code
    val stateApiData = bData[position].state
    val streetApiData = bData[position].street
    val apiLastUpdated = bData[position].updated_at

    val lastUpdated = apiLastUpdated?.let { viewModel.dateTextConverter(it) }
    val phoneNumberApiData = bData[position].phone
    val websiteUrlApiData = bData[position].website_url
    var expanded by remember { mutableStateOf(false) }

    val clickableWebsiteText = makeLink(websiteUrlApiData)
    val clickablePhoneNumberText = makeLink(phoneNumberApiData)


    Box(

    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .padding(10.dp)
                .clickable { expanded = !expanded }
        ) {
            drawRoundRect(
                color = color,
                size = size,
                cornerRadius = CornerRadius(10.dp.toPx())
            )
        }

        Column(verticalArrangement = Arrangement.Center) {
            //Number text for position of card
            Text(
                text = cardNumber.toString(),
                modifier = Modifier.padding(18.dp),
                color = Color.Black,
                fontSize = 12.sp,
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