package com.samm.brewerysearch.presentation.screens.main.util

import com.samm.brewerysearch.domain.models.BrewData

val TitleComparator = Comparator<BrewData> { left, right ->
    left.name!!.compareTo(right.name!!)
}

val CityComparator = Comparator<BrewData> { left, right ->
    left.city!!.compareTo(right.city!!)
}
