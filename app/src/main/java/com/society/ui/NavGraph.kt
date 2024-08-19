package com.society.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.society.repository.AnnouncementRepository
import com.society.repository.EventsRepository
import com.society.ui.screens.AnnouncementScreen
import com.society.ui.screens.DashboardScreen
import com.society.ui.screens.EventsScreen

import com.society.ui.screens.LoginScreen
import com.society.ui.screens.MySocietyScreen
import com.society.ui.screens.PaymentScreen
import com.society.ui.screens.RegisterScreen
import com.society.ui.screens.StartScreen
import com.society.viewModel.AuthViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "start") {
        composable("start") { StartScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController,viewModel = AuthViewModel()) }
        composable("dashboard") { DashboardScreen(navController) }
        composable("societies") { MySocietyScreen(/* ViewModel and other params as needed */) }
        composable("events") { EventsScreen(EventsRepository()) }
        composable("payment") { PaymentScreen() }
        composable("announcements") { AnnouncementScreen(AnnouncementRepository()) }

        // Add other screens
    }
}