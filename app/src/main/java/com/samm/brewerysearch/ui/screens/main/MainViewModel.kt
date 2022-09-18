package com.samm.brewerysearch.ui.screens.main

import androidx.lifecycle.ViewModel
import com.samm.brewerysearch.data.BrewData
import com.samm.brewerysearch.data.DataOrException
import com.samm.brewerysearch.domain.repository.BrewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: BrewRepository): ViewModel(){
    suspend fun getData(search: String?)
            : DataOrException<List<BrewData>, Boolean, Exception> {
        return repository.getData(search)
    }
}