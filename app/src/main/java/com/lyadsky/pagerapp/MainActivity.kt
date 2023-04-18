package com.lyadsky.pagerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.lyadsky.pagerapp.navigation.MainNavigationScreen
import com.lyadsky.pagerapp.navigation.Navigator
import com.lyadsky.pagerapp.navigation.ScreenRoute
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

    private val startDestination: ScreenRoute = ScreenRoute.Main
    private val rootNavigation: Navigator by inject { parametersOf(startDestination) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            rootNavigation.init(navController)
            MainNavigationScreen(startDestination = ScreenRoute.Main, navController = navController)
        }
    }
}
