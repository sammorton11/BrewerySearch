package com.samm.brewerysearch.ui.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.samm.brewerysearch.data.models.BrewData
import com.samm.brewerysearch.data.DataOrException
import com.samm.brewerysearch.ui.navigation.Screens
import com.samm.brewerysearch.ui.theme.DeepOrange200
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    search: String?)
{
    val apiData = brewData(mainViewModel = mainViewModel, search = search)

    Scaffold(
        content = {

            if (apiData.loading == true){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }

            } else if (apiData.data != null){
                MainContent(bData = apiData.data!!, navController)
            }

        },
        topBar = { BrewTopBar(navController, search) }
    )

}

@Composable
fun BrewTopBar(navController: NavController, search: String?) {

   TopAppBar(modifier = Modifier
       .height(55.dp)
       .fillMaxWidth(),

        title = {
            Text(
                "Breweries",
                maxLines = 1
            )
        },

        actions = {

            Text(text = "$search")

            IconButton(onClick = { navController.navigate(Screens.SearchScreen.name) }) {
                Icon(
                    modifier = Modifier.padding(10.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }
        }
    )

}

//Gets data from view model
@Composable
fun brewData(
    mainViewModel: MainViewModel, search: String?
): DataOrException<List<BrewData>, Boolean, Exception> {

    return produceState<DataOrException<List<BrewData>, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getData(search)
    }.value
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainContent(bData: List<BrewData>, navController: NavController){

    val allBreweries = bData.size

    Column(
        modifier = Modifier
            .padding(bottom = 15.dp, top = 15.dp, start = 5.dp)){

        Text(text = "Amount: ${bData.size}")

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(5.dp)
        ){
            items(allBreweries){index ->
                Breweries(bData = bData, position = index, navController = navController)
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Breweries(bData: List<BrewData>, position: Int, navController: NavController){

    val context = LocalContext.current
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
    val lastUpdated = dateTextConverter(apiLastUpdated)
    val websiteUrlApiData = bData[position].website_url

    var expanded by remember { mutableStateOf(false) }
    val clickableWebsiteText = buildAnnotatedString {
        if (websiteUrlApiData != null) {
            append(websiteUrlApiData)
        }
    }

    Column(
        Modifier
         //   .fillMaxSize()
            .padding(10.dp)
    ) {


        //Brewery Card
        Card(
            backgroundColor = DeepOrange200,
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    enabled = true,
                    onClickLabel = "",
                    onClick = { expanded = !expanded }
                ),
            contentColor = Color.Black

        ) {
            Column(
                Modifier
                    .padding(5.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {

                //Number text for position of card
                Text(
                    text = cardNumber.toString(),
                    fontSize = 10.sp,
                    modifier = Modifier.align(Alignment.Start)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        //.padding(15.dp)
                ) {

                    // Name of Brewery
                    Text(
                        text = bData[position].name,
                        modifier = Modifier
                            .padding(10.dp),
                        softWrap = true,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 1.sp,
                            textAlign = TextAlign.Center,
                            shadow = Shadow(
                                color = Color.Gray,
                                offset = Offset(8f, 8f),
                                blurRadius = 8f
                            ),
//                        textGeometricTransform = TextGeometricTransform(
//                            scaleX = 2.5f,
//                            skewX = 1f
//                        )
                        )

                    )

                }

                //Brewery Details
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (expanded){
                        Text(text = "City:  $cityApiData")
                        Text(text = "State:  $stateApiData")
                        Text(text = "Street:  $streetApiData")
                        Text(text = "Phone:  $phoneNumberApiData")
                        Text(text = "Country:  $countryApiData")
                        Text(text = "Type:  $breweryTypeApiData")
                        Text(text = "County:  $countyApiData")
                        Text(text = "Postal Code:  $postalCodeApiData")
                        Text(text = "Last updated:  $lastUpdated")



                    }
                }

                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    ClickableText(
                        text = clickableWebsiteText,
                        modifier = Modifier.padding(top = 10.dp),
                        onClick = {
                            if (websiteUrlApiData != null) {
                                openWebsite(websiteUrlApiData, context)
                            }
                        }
                    )
                }

            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun dateTextConverter(date: String): String{
    val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
    val text = date.format(formatter)
    val parsedDate = LocalDate.parse(text, formatter)
    return parsedDate.toString()

}

fun openWebsite(website: String, context: Context){
    val urlIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(website)
    )
    context.startActivity(urlIntent)
}