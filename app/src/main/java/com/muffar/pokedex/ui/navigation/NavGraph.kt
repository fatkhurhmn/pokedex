package com.muffar.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.muffar.pokedex.presentation.detail.DetailScreen
import com.muffar.pokedex.presentation.detail.DetailScreenViewModel
import com.muffar.pokedex.presentation.home.HomeScreen
import com.muffar.pokedex.presentation.home.HomeViewModel

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Screen.MainRoute.route,
            startDestination = Screen.Home.route
        ) {
            composable(route = Screen.Home.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                HomeScreen(
                    onEvent = viewModel::onEvent,
                    state = viewModel.state.value,
                    navigate = {
                        navController.navigate(Screen.Detail.createRoute(it.id))
                    }
                )
            }

            composable(route = Screen.Detail.route) {
                val viewModel: DetailScreenViewModel = hiltViewModel()
                DetailScreen(
                    state = viewModel.state.value,
                    navigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}