package com.krishan.countryquest.data.remote.dto

data class RestCountryDto(
    val name: Name,
    val capital: List<String>,
    val region: String,
    val subregion: String,
    val languages: Map<String, String>
)

data class Name(val common: String, val official: String)
