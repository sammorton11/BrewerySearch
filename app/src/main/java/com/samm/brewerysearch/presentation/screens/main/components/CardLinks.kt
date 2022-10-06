package com.samm.brewerysearch.presentation.screens.main.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.samm.brewerysearch.presentation.screens.main.MainViewModel
import com.samm.brewerysearch.presentation.screens.main.util.LinkBuilder


@Composable
fun CardLinks(
    phoneNumberText: AnnotatedString,
    phoneNumberData: String?,
    websiteText: AnnotatedString,
    websiteUrlData: String?,
    viewModel: MainViewModel,
    context: Context
){
    //Fourth Row
    Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ){

        Column(
            modifier = Modifier.padding(
                start = 10.dp, end = 10.dp,
                top = 15.dp, bottom = 15.dp
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Phone Number Link
            LinkBuilder(
                phoneNumberText,
                phoneNumberData,
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                if (phoneNumberData != null) {
                    viewModel.callNumber(phoneNumberData, context)
                }
            }
            //Website Link
            LinkBuilder(
                websiteText,
                websiteUrlData,
                modifier = Modifier.padding(bottom = 15.dp),
                intentCall = {
                    if (websiteUrlData != null) {
                        viewModel.openWebsite(websiteUrlData, context)
                    }
                }
            )
        }
    }
}
