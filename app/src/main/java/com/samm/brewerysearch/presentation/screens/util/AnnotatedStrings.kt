package com.samm.brewerysearch.presentation.screens.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

fun makeLink(text: String?): AnnotatedString{
    return buildAnnotatedString {
        if (text != null) {
            append(text)
        }
    }
}