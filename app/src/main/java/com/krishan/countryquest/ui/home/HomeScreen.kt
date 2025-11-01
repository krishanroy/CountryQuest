package com.krishan.countryquest.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(paddingValues: PaddingValues) {
    val viewModel: HomeViewModel = viewModel()
    val state = viewModel.homeScreenStateFlow.collectAsStateWithLifecycle().value

    Column(modifier = Modifier.padding(paddingValues)) {
        Text(modifier = Modifier.padding(vertical = 16.dp, horizontal = 10.dp), text = "Check out all independent nations!")

        when (state) {
            is HomeScreenState.Loading -> Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }

            is HomeScreenState.Error -> Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    "Something went wrong!",
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.error)
                )
            }

            is HomeScreenState.Success -> LazyColumn {
                items(state.independentCountries) { independentCountry ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(60.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart,) {
                            Text(modifier = Modifier.padding(12.dp), text = independentCountry.name.common,)
                        }
                    }
                }
            }
        }
    }
}