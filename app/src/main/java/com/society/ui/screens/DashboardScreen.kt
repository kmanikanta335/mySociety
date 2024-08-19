package com.society.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.society.R
import com.society.viewModel.AuthViewModel

@Composable
fun DashboardScreen(navController: NavHostController) {
    val viewModel: AuthViewModel = viewModel()
    var userName by remember { mutableStateOf<String?>("User!") }

    // Get current user from FirebaseAuth
    LaunchedEffect(Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        userName = currentUser?.displayName ?: extractNameFromEmail(currentUser?.email)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        //.padding(paddingValues)
    ) {
        // Background image
        Image(
            painter = painterResource(R.drawable.cityspace), // Replace with the image source
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopCenter
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.Start
        ){
            Spacer(modifier = Modifier.height(128.dp))
            Text(
                text = "Hello, $userName!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(Color(0xB3FFFFFF)) // Slightly transparent white
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Society",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Grid of options
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val options = listOf(
                    "My Society" to R.drawable.mys to "societies", // Replace with your drawable resources
                    "Events" to R.drawable.event to "events",
                    "Payment" to R.drawable.pay to "payment",
                    "Announcement" to R.drawable.ann to "announcements"
                )
                items(options.size) { index ->
                    OptionCard(
                        title = options[index].first.first,
                        iconRes = options[index].first.second,
                        route = options[index].second,
                        navController = navController
                    )
                }
            }
        }
    }
}

fun extractNameFromEmail(email: String?): String {
    return email?.substringBefore("@")?.replace(".", " ")?.capitalizeWords() ?: "User"
}

// Extension function to capitalize the first letter of each word
fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }
@Composable
fun OptionCard(title: String, iconRes: Int,route:String,navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f).clickable {
                      navController.navigate(route)
            }

        ,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = title,
                    modifier = Modifier.size(88.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
