package com.society.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.society.R


@Composable
fun StartScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
       // CityscapeImage(painter = painterResource(id = R.drawable.ic_cityscape))

        // Overlaying content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(16.dp))

           // HomeIcon(painter = painterResource(id = R.drawable.ic_home))

            Spacer(modifier = Modifier.height(16.dp))

            WelcomeText()

            Spacer(modifier = Modifier.height(16.dp))

            ActionButtons(navController)
        }
    }
}

@Composable
fun HomeIcon(painter: Painter) {
    Image(
        painter = painter,
        contentDescription = "Home Icon",
        modifier = Modifier.size(60.dp)
    )
}

@Composable
fun WelcomeText() {
    Text(
        text = "GET SMART SOCIETY!",
        fontSize = 28.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "The world is moving at an incredibly exciting, sometimes overwhelming pace of change. Societies are facing huge challenges drawn from an ever-growing, aging population",
        fontSize = 16.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 24.dp)
    )
}

@Composable
fun CityscapeImage(painter: Painter) {
    Image(
        painter = painter,
        contentDescription = "Cityscape",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ActionButtons(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = { navController.navigate("login") },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Login", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { navController.navigate("register") },
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            border = ButtonDefaults.outlinedButtonBorder
        ) {
            Text(text = "Register", color = Color.White, fontSize = 18.sp)
        }
    }
}