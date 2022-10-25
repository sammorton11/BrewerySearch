package com.samm.brewerysearch.presentation.screens.util

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.samm.brewerysearch.R
import com.samm.brewerysearch.domain.models.BrewData

@Composable
fun LinkBuilder(
    clickableText: AnnotatedString,
    dataText: String?,
    modifier: Modifier,
    intentCall: (String?) -> Unit
){
    if (dataText != null){

        ClickableText(
            text = clickableText,
            modifier = modifier,
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                letterSpacing = 2.sp
            ),
            onClick = {
                intentCall(dataText)
            }
        )
    }
    else {
        Text(
            text = stringResource(R.string.Not_Available),
            color = Color.Gray,
            fontSize = 10.sp
        )
    }
}