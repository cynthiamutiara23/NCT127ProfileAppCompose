package com.dicoding.mycomposeapp.ui.navigation

sealed class Screen(val route: String) {
    object List : Screen("list")
    object Detail : Screen("list/{memberId}") {
        fun createRoute(memberId: Long) = "list/$memberId"
    }
    object About : Screen("about")
}