package com.samm.brewerysearch.presentation.screens.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samm.brewerysearch.R
import com.samm.brewerysearch.data.DataOrException
import com.samm.brewerysearch.data.models.BrewData
import com.samm.brewerysearch.presentation.screens.main.components.*


/*
    TODO:
*/

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    search: String?
) {
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainContent(bData: List<BrewData>, viewModel: MainViewModel){

    val allBreweries = bData.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey_blue))
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            // Amount text label
            Text(
                text = "Result(s): ${bData.size}",
                modifier = Modifier.padding(top = 15.dp, start = 15.dp, bottom = 5.dp)
            )
        }

        // List of Brewery cards
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(5.dp)
        ){
            items(allBreweries){ index ->
                Breweries(
                    bData = bData, // Todo: create a way to select different sorting conditions
                    position = index,
                    viewModel = viewModel
                )
            }
        }
    }
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
fun Breweries(
    bData: List<BrewData>,
    position: Int,
    viewModel: MainViewModel
){
    Column(
        Modifier.padding(10.dp)
    ){
        BreweryCard(bData, position, viewModel)
    }
}
