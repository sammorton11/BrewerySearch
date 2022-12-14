package com.samm.brewerysearch.presentation.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samm.brewerysearch.R
import com.samm.brewerysearch.presentation.navigation.Screens
import kotlinx.coroutines.delay


//Todo: Center the animation element

@Composable
fun SplashScreen(navController: NavController){

    val defaultCity = "Oklahoma City"

    val scale = remember {
        Animatable(0f)
    }

    // animates the splash screen
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                }
            )
        )
        delay(2000L)
        navController.navigate(Screens.MainScreen.name + "/$defaultCity")
    })

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .padding(35.dp)
                .size(330.dp)
                .scale(scale.value)
                .fillMaxSize(),
            shape = CircleShape,
            color = colorResource(id = R.color.pink),
            border = BorderStroke(width = 2.dp, color = colorResource(id = R.color.light_blue)),

            ){

            Column (
                modifier = Modifier
                    .padding(1.dp)
                    .background(colorResource(id = R.color.grey_blue))
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {

                Image (
                    painter = painterResource(id = R.drawable.ic_baseline_sports_bar_24),
                    contentDescription = "Beer",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(105.dp)
                )

                Text (
                    text = "Breweries",
                    style = MaterialTheme.typography.h5,
                    color = Color.Black
                )
            }
        }
    }
}