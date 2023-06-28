package com.dicoding.mycomposeapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.mycomposeapp.ui.navigation.Screen
import com.dicoding.mycomposeapp.ui.screen.about.AboutScreen
import com.dicoding.mycomposeapp.ui.screen.detail.DetailScreen
import com.dicoding.mycomposeapp.ui.screen.list.ListScreen
import com.dicoding.mycomposeapp.ui.theme.MyComposeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyComposeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == Screen.List.route) {
                MoreMenu(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.List.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.List.route) {
                ListScreen(
                    navigateToDetail = { memberId ->
                        navController.navigate(Screen.Detail.createRoute(memberId))
                    }
                )
            }
            composable(Screen.About.route) {
               AboutScreen(
                   navigateBack = {
                       navController.navigateUp()
                   }
               )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("memberId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("memberId") ?: -1L
                DetailScreen(
                    memberId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreMenu(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(stringResource(R.string.top_bar)) },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(Screen.About.route)
                },
                modifier = modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.about_page),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Preview
@Composable
fun MyComposeAppPreview() {
    MyComposeAppTheme {
        MyComposeApp()
    }
}