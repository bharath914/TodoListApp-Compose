package com.bharath.todolistapplication.ui.navigation

sealed class Screens(val route: String) {

    data object Home : Screens("home")
    data object AddNote : Screens("addNote")

}