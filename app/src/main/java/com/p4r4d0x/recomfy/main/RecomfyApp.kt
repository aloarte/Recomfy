package com.p4r4d0x.recomfy.main

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.p4r4d0x.recomfy.main.compose.DetailScreen
import com.p4r4d0x.recomfy.search.SearchScreen
import com.p4r4d0x.recomfy.theme.RecomfyTheme

@Composable
fun RecomfyApp(viewModel: MainViewModel) {
    val navController = rememberNavController()
    RecomfyTheme {
        NavHost(
            navController = navController,
            startDestination = NavDestinations.Search.route
        ) {
            composable(route = NavDestinations.Search.route) { from ->
                SearchScreen(viewModel, onOpenDetail = {
                    navController.navigate("${NavDestinations.Detail}/${it.name}")
                })
            }
            composable(
                route = "${NavDestinations.Detail}/{${NavDestinations.ITEM_NAME_KEY}}",
                arguments = listOf(navArgument(NavDestinations.ITEM_NAME_KEY) { type = NavType.StringType })
            ) { backStackEntry ->
                val arguments = requireNotNull(backStackEntry.arguments)
                val itemDataName = arguments.getString(NavDestinations.ITEM_NAME_KEY)
                itemDataName?.let{
                    DetailScreen(viewModel, itemDataName) { navController.navigateUp() }

                }
            }

        }
    }

}
