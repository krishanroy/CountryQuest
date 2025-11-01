package com.krishan.countryquest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishan.countryquest.data.remote.CountriesApi
import com.krishan.countryquest.data.remote.RetrofitSingleton
import com.krishan.countryquest.data.remote.dto.RestCountryDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel : ViewModel() {
    private val _homeScreenMutableStateFlow: MutableStateFlow<HomeScreenState> =
        MutableStateFlow(HomeScreenState.Loading)
    val homeScreenStateFlow = _homeScreenMutableStateFlow.asStateFlow().onStart {
        getAllIndependentCountries()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = HomeScreenState.Loading
    )

    fun getAllIndependentCountries() {
        viewModelScope.launch {
            val client = RetrofitSingleton.retrofit.create(CountriesApi::class.java)
            try {
                val response = client.getAllIndependentCountries()
                if (response.isSuccessful && response.body() != null) {
                    _homeScreenMutableStateFlow.value =
                        HomeScreenState.Success(independentCountries = response.body() ?: emptyList())
                } else {
                    _homeScreenMutableStateFlow.value = HomeScreenState.Error(message = "Something went wrong")
                }

            } catch (e: Exception) {
                Timber.tag(tag = "HomeViewModel ::getAllIndependentCountries").e(e.localizedMessage)
                _homeScreenMutableStateFlow.value = HomeScreenState.Error(error = e, message = e.localizedMessage)
            }
        }
    }
}

sealed class HomeScreenState {
    data object Loading : HomeScreenState()
    data class Success(val independentCountries: List<RestCountryDto>) : HomeScreenState()
    data class Error(val error: Exception? = null, val message: String?) : HomeScreenState()
}
