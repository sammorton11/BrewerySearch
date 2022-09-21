package com.samm.brewerysearch.ui.screens.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.samm.brewerysearch.data.models.BrewData
import com.samm.brewerysearch.data.DataOrException
import com.samm.brewerysearch.domain.repository.BrewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: BrewRepository): ViewModel(){

    suspend fun getData(search: String?)
            : DataOrException<List<BrewData>, Boolean, Exception> {
        return repository.getData(search)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateTextConverter(date: String): String{
        val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        val text = date.format(formatter)
        val parsedDate = LocalDate.parse(text, formatter)
        return parsedDate.toString()

    }

    fun openWebsite(website: String, context: Context){
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(website)
        )
        context.startActivity(urlIntent)
    }

    fun callNumber(phoneNumber: String, context: Context){
        val urlIntent = Intent(
            Intent.ACTION_DIAL,
            Uri.parse("tel: $phoneNumber")
        )
        context.startActivity(urlIntent)
    }
}