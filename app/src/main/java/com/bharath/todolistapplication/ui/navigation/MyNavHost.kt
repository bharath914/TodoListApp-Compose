package com.bharath.todolistapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bharath.todolistapplication.presentation.addTodo.AddTodoScreen
import com.bharath.todolistapplication.presentation.home.HomeScreen

@Composable
fun MyNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            HomeScreen(navHostController = navHostController)
        }
        composable(Screens.AddNote.route + "/{Id}") {
            AddTodoScreen(navHostController = navHostController)
        }
    }
}