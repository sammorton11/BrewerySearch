package com.samm.brewerysearch.presentation.screens.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samm.brewerysearch.domain.models.BrewData
import com.samm.brewerysearch.presentation.screens.main.components.BrewTopBar
import com.samm.brewerysearch.presentation.screens.main.components.BreweryCard
import com.samm.brewerysearch.presentation.screens.main.components.brewData
import com.samm.brewerysearch.presentation.screens.main.util.CityComparator
import com.samm.brewerysearch.presentation.screens.main.util.TitleComparator
import com.samm.brewerysearch.presentation.ui.theme.*


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
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }

            } else if (apiData.data != null){
                MainContent(
                    bData = apiData.data!!,
                    viewModel = mainViewModel
                )
            }
        },
        topBar = { BrewTopBar(navController, search) }
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainContent(bData: List<BrewData>, viewModel: MainViewModel){

    val allBreweries = bData.size
    var comparator by remember { mutableStateOf(TitleComparator) }
    val colorList: List<Color> = listOf(RedOrange, RedPink, BabyBlue, Violet, LightGreen)

    Column {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {

            Row(
                modifier = Modifier.padding(15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Amount text label
                Text(
                    text = "Result(s): ${bData.size}",
                    modifier = Modifier
                        .semantics {
                            this.contentDescription = "Results"
                        }
                        .padding(end = 15.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                ) {

                    Button(
                        onClick = { comparator = TitleComparator }
                    ){
                        Text(text = "Title")
                    }

                    Button(
                        onClick = { comparator = CityComparator },
                        Modifier
                            .padding(start = 15.dp),

                        ){
                        Text(text = "City")
                    }
                }
            }
        }

        // List of Brewery cards
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(5.dp)
        ){


            items(allBreweries){ index ->
                BreweryCard (
                    bData.sortedWith(comparator),
                    index,
                    viewModel,
                    colorList[index % colorList.size]
                )

            }
        }
    }
}



