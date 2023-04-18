package com.lyadsky.pagerapp.navigation

import androidx.navigation.NavHostController

enum class ScreenRoute() {
    Main,
    CharacterInfo
}

interface Navigator {
    fun init(navHostController: NavHostController)

    fun navigateBack()
    fun navigateMain()
    fun navigateCharacterInfo(id: Int)
}

class NavigatorImpl(startDestination: ScreenRoute): Navigator {
    private var currentMainStack = startDestination
    private lateinit var navController: NavHostController

    override fun init(navHostController: NavHostController) {
        navController = navHostController
    }

    override fun navigateBack() {
        navController.navigateUp()
    }

    override fun navigateMain() {
        navigateToNavBarDestination(ScreenRoute.Main)
    }

    override fun navigateCharacterInfo(id: Int) {
        navController.navigate("${ScreenRoute.CharacterInfo.name}/$id")
    }

    private fun navigateToNavBarDestination(root: ScreenRoute) {
        navController.navigate(root.name) {
            popUpTo(navController.graph.id) {
                if (root != currentMainStack) saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }.also { currentMainStack = root }
    }
}
