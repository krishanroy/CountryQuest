package com.krishan.countryquest.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RestCountryDto(
    val name: Name,
    val capital: List<String>,
    val region: String,
    val subregion: String,
    val languages: Map<String, String>
)

@Serializable
data class Name(val common: String, val official: String)
