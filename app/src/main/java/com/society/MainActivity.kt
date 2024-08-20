package com.society

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.society.ui.NavGraph
import com.society.ui.screens.SplashScreen
import com.society.ui.screens.StartScreen
import com.society.ui.theme.SocietyTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            val statusBarColor = colorResource(id = R.color.red)
            SideEffect {
                window.statusBarColor = statusBarColor.toArgb()
            }

            SocietyTheme(darkTheme = false) {
                 val navController = rememberNavController()
                    // A surface container using the 'background' color from the theme
                    Scaffold(
                        topBar = {
                            CenterAlignedTopAppBar(
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = colorResource(id = R.color.red),
                                    titleContentColor = Color.Black,
                                ),
                                modifier = Modifier,

                                title = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center, // Center items horizontally within the Row
                                        modifier = Modifier.fillMaxWidth() // Make the Row take up the full width of the TopAppBar
                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.ic_home),
                                            contentDescription = "app Logo",
                                            modifier = Modifier
                                                .height(60.dp)
                                                .padding(end = 8.dp), // Add some space between the image and text
                                        )
                                        Text(
                                            text = "MY SOCIETY",
                                            fontSize = 28.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                        )
                                    }

                                }
                            )

                        },
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.background_image),
                                contentDescription = "Background Image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.FillBounds
                            )

                            NavGraph(navController = navController)
                        }
                    }

            }
        }
    }
}



