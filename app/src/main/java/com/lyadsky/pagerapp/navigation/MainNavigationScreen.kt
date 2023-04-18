package com.lyadsky.pagerapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lyadsky.pagerapp.features.characterInfo.CharacterInfoScreen
import com.lyadsky.pagerapp.features.main.MainScreen

@Composable
fun MainNavigationScreen(startDestination: ScreenRoute, navController: NavHostController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            navController = navController,
            startDestination = startDestination.name
        ) {

            composable(ScreenRoute.Main.name) {
                MainScreen()
            }
            composable(
                route = "${ScreenRoute.CharacterInfo.name}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("id")
                CharacterInfoScreen(id!!)
            }
        }
    }
}
