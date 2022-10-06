package com.samm.brewerysearch.presentation.screens.main

import com.samm.brewerysearch.data.models.BrewData

data class BreweryCardState (
    val brewData: List<BrewData> = emptyList()
)