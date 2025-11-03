package com.krishan.countryquest.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.krishan.countryquest.data.remote.dto.RestCountryDto
import com.krishan.countryquest.ui.details.DetailScreen
import com.krishan.countryquest.ui.home.HomeScreen
import kotlin.reflect.typeOf

@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues, startDestination: Screen) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<Screen.Home> {
            HomeScreen(paddingValues = paddingValues, navController)
        }
        composable<Screen.Detail>(typeMap = mapOf(typeOf<RestCountryDto>() to serializableType<RestCountryDto>())
        ) {backStackEntry ->
            val restCountry = requireNotNull(backStackEntry.toRoute<Screen.Detail>()).country
            DetailScreen(country = restCountry)
        }
    }
}

