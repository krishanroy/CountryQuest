package com.krishan.countryquest.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.krishan.countryquest.data.remote.dto.RestCountryDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(country: RestCountryDto) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Country Details Screen")
        }, navigationIcon = {
            IconButton(onClick = {}) {

            }
        })
    }) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            // todo: a hero card for map of the country that opens Google Map
            Text(country.name.common)
            Text(country.capital.first())
            Text(country.languages.values.first())
        }
    }
}