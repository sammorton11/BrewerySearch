package com.samm.brewerysearch.presentation.screens.search.util

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.samm.brewerysearch.presentation.ui.theme.Violet

@Composable
fun MyAppTextFieldColors(
    textColor: Color = Color.White,
    disabledTextColor: Color = Color.White,
    backgroundColor: Color = Violet,
    cursorColor: Color = Color.White,
    errorCursorColor: Color = Color.Red,
) = TextFieldDefaults.textFieldColors(
    textColor = textColor,
    disabledTextColor = disabledTextColor,
    backgroundColor = backgroundColor,
    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor,
)