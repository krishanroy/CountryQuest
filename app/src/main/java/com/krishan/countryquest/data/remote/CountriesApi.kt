package com.krishan.countryquest.data.remote

import com.krishan.countryquest.data.remote.dto.RestCountryDto
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {
    @GET("independent?status=true")
    suspend fun getAllIndependentCountries(): Response<List<RestCountryDto>>
}