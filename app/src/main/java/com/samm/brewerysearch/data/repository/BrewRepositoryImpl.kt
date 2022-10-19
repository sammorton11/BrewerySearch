package com.samm.brewerysearch.data.repository

import com.samm.brewerysearch.data.DataOrException
import com.samm.brewerysearch.data.network.BrewApi
import com.samm.brewerysearch.domain.models.BrewData
import com.samm.brewerysearch.domain.repository.BrewRepository
import javax.inject.Inject

class BrewRepositoryImpl @Inject constructor(private val api: BrewApi): BrewRepository {

    override suspend fun getData(search: String?)
    : DataOrException<List<BrewData>, Boolean, Exception> {

        val response = try {
            api.getData(search)
        } catch (e: Exception){
            return DataOrException(e = e)
        }

        return DataOrException(data = response)
    }
}
