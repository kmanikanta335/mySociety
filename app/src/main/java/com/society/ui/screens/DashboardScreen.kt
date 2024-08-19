package com.society.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DashboardScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Dashboard", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Societies Button
        Button(onClick = { navController.navigate("societies") }, modifier = Modifier.fillMaxWidth()) {
            Text("View Societies")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Events Button
        Button(onClick = { navController.navigate("events") }, modifier = Modifier.fillMaxWidth()) {
            Text("View Events")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Payment Button
        Button(onClick = { navController.navigate("payment") }, modifier = Modifier.fillMaxWidth()) {
            Text("Make a Payment")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Announcements Button
        Button(onClick = { navController.navigate("announcements") }, modifier = Modifier.fillMaxWidth()) {
            Text("View Announcements")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Optionally display some summary information
        Text("Welcome to the Smart Society App!", style = MaterialTheme.typography.bodyLarge)
    }
}