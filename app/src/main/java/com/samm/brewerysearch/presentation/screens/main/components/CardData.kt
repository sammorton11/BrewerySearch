package com.samm.brewerysearch.presentation.screens.main.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import com.samm.brewerysearch.data.DataOrException
import com.samm.brewerysearch.domain.models.BrewData
import com.samm.brewerysearch.presentation.screens.main.MainViewModel

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