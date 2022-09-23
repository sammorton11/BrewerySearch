package com.samm.brewerysearch.domain.repository

import android.util.Log
import com.samm.brewerysearch.data.DataOrException
import com.samm.brewerysearch.data.models.BrewData
import com.samm.brewerysearch.data.network.BrewApi
import javax.inject.Inject

// get data from api
class BrewRepository @Inject constructor(private val api: BrewApi) {

    suspend fun getData(search: String?)
            : DataOrException<List<BrewData>, Boolean, Exception> {
        val response = try {
            api.getData(search)
        } catch (e: Exception){
            Log.d("ERROR", "getData: $e")
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}