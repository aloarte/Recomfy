package com.p4r4d0x.recomfy.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.p4r4d0x.recomfy.search.SearchScreen
import com.p4r4d0x.recomfy.theme.RecomfyTheme

@Composable
fun RecomfyApp(viewModel: MainViewModel) {
    val navController = rememberNavController()
    RecomfyTheme {
        NavHost(
            navController = navController,
            startDestination = Screen.Search.route
        ) {
            composable(Screen.Search.route) { from ->
                SearchScreen(viewModel, onOpenDetail = {})
            }
        }
    }

}
