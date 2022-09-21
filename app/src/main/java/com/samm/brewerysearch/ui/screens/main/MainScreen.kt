package com.samm.brewerysearch.ui.screens.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.samm.brewerysearch.R
import com.samm.brewerysearch.data.DataOrException
import com.samm.brewerysearch.data.models.BrewData
import com.samm.brewerysearch.ui.navigation.Screens


// TODO: Better Colors!


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
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.grey_blue)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }

            } else if (apiData.data != null){
                MainContent(bData = apiData.data!!, viewModel = mainViewModel)
            }

        },

        topBar = { BrewTopBar(navController, search) }
    )
}



@Composable
fun BrewTopBar(navController: NavController, search: String?) {

   TopAppBar(

       modifier = Modifier
           .height(55.dp)
           .fillMaxWidth(),
        title = {
            Text(
                stringResource(id = R.string.main_title),
                style = TextStyle(
                    // Todo: make a cool text style
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 1
            )
        },
        actions = {

            Text(text = "$search")

            IconButton(onClick = { navController.navigate(Screens.SearchScreen.name) }) {
                Icon(
                    modifier = Modifier.padding(10.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.search)
                )
            }
        },
       backgroundColor = colorResource(id = R.color.light_purple)
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
fun MainContent(bData: List<BrewData>, viewModel: MainViewModel){

    val allBreweries = bData.size


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey_blue))
    ){

        // Amount text label
        Text(
            text = "Result(s): ${bData.size}",
            modifier = Modifier.padding(top = 15.dp, start = 15.dp, bottom = 5.dp)
        )

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(5.dp)
        ){
            items(allBreweries){index ->
                Breweries(
                    bData = bData,
                    position = index,
                    viewModel = viewModel
                )
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Breweries(
    bData: List<BrewData>,
    position: Int,
    viewModel: MainViewModel
){

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

    Column(
        Modifier.padding(10.dp)
    ) {

        //Brewery Card
        Card(
            backgroundColor = colorResource(id = R.color.light_blue),
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    enabled = true,
                    onClickLabel = "",
                    onClick = { expanded = !expanded }
                ),

            contentColor = Color.Black,
            border = BorderStroke(0.5.dp, colorResource(id = R.color.pink)),
            elevation = 15.dp

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
                    modifier = Modifier.fillMaxSize()
                ) {

                    // Name of Brewery
                    Text(
                        text = bData[position].name!!,
                        modifier = Modifier.padding(top = 25.dp),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        softWrap = true,
                        style = TextStyle(
                            color = colorResource(id = R.color.light_purple),
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 1.sp,
                           // textAlign = TextAlign.Center,
//                            shadow = Shadow(
//                                color = Color.Gray,
//                                offset = Offset(8f, 8f),
//                                blurRadius = 15f
//                            ),
                            fontStyle = FontStyle.Normal,

                        )

                    )

                }

                //Brewery Details
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (expanded){

                        Spacer(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
                        Text(text = "City:  $cityApiData")
                        Text(text = "State:  $stateApiData")
                        Text(text = "Street:  $streetApiData")
                        Text(text = "Phone:  $phoneNumberApiData")
                        LinkBuilder(
                            clickablePhoneNumberText,
                            phoneNumberApiData,
                        ) {
                            if (phoneNumberApiData != null) {
                                viewModel.callNumber(phoneNumberApiData, context)
                            }
                        }
                        Text(text = "Country:  $countryApiData")
                        Text(text = "County:  $countyApiData")
                        Text(text = "Postal Code:  $postalCodeApiData")
                        Spacer(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
                        Text(text = "Type:  $breweryTypeApiData")
                        Text(text = "Last updated:  $lastUpdated")
                        Spacer(modifier = Modifier.padding(bottom = 10.dp))
                    }
                }


                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    LinkBuilder(
                        clickableWebsiteText,
                        websiteUrlApiData ,
                        intentCall = {
                            if (websiteUrlApiData != null) {
                                viewModel.openWebsite(websiteUrlApiData, context)
                            }
                        }
                    )

                }
            }
        }
    }
}


//@Composable
//fun PhoneNumberLink(clickablePhoneNumberText: AnnotatedString, phoneNumber: String?, viewModel: MainViewModel, context: Context){
//    if (phoneNumber != null){
//        ClickableText(
//            text = clickablePhoneNumberText,
//            modifier = Modifier
//                .padding(bottom = 15.dp),
//            style = TextStyle(
//                textDecoration = TextDecoration.Underline
//            ),
//            onClick = {
//                viewModel.callNumber(phoneNumber, context)
//            }
//        )
//    }
//    else {
//        Text(
//            text = "Sorry, Not Available",
//            color = Color.Gray,
//            fontSize = 10.sp
//        )
//    }
//}


@Composable
fun LinkBuilder(clickableText: AnnotatedString, dataText: String?, intentCall: (String?) -> Unit){
    if (dataText != null){
        ClickableText(
            text = clickableText,
            modifier = Modifier
                .padding(bottom = 15.dp),
            style = TextStyle(
                textDecoration = TextDecoration.Underline
            ),
            onClick = {
                intentCall(dataText)
            }
        )
    }
    else {
        Text(
            text = "Sorry, Not Available",
            color = Color.Gray,
            fontSize = 10.sp
        )
    }
}