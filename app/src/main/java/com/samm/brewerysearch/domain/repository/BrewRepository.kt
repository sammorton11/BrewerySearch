package com.samm.brewerysearch.domain.repository

import com.samm.brewerysearch.data.DataOrException
import com.samm.brewerysearch.domain.models.BrewData

// get data from api
interface BrewRepository {

    suspend fun getData(search: String?): DataOrException<List<BrewData>, Boolean, Exception>
}