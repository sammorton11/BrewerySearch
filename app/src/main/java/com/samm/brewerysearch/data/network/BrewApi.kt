package com.samm.brewerysearch.data.network

import com.samm.brewerysearch.domain.models.BrewData
import retrofit2.http.GET
import retrofit2.http.Query

interface BrewApi {

    @GET(value = "breweries/search?")
    suspend fun getData(
        @Query("query")
        search: String?
    ): List<BrewData>
}