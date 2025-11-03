package com.krishan.countryquest.ui.navigation

import com.krishan.countryquest.data.remote.dto.RestCountryDto
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data class Detail(val country: RestCountryDto) : Screen()
}