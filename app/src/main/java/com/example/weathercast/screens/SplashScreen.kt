package com.example.weathercast.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weathercast.R
import com.example.weathercast.navigation.AppScreen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navHostController: NavHostController) {

    val animSize = remember {
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(key1 = true) {

        animSize.animateTo(
            targetValue = 1.1f,
            animationSpec = tween(durationMillis = 1500, delayMillis = 100)
        )
        delay(timeMillis = 1000)
        navHostController.navigate(AppScreen.MainScreen.route){
            popUpTo(AppScreen.SplashScreen.route){
                inclusive = true
            }
        }

    }


    Surface(modifier = Modifier.fillMaxSize(), color = Color.Cyan) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                modifier = Modifier
                    .size(300.dp)
                    .scale(animSize.value),
                shape = CircleShape,
                border = BorderStroke(2.dp, Color.Gray),
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = null,
                        contentScale = ContentScale.Fit, modifier = Modifier.size(100.dp)
                    )


                    Text(
                        text = "Embrace Your Day",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(top = 10.dp)
                    )


                }


            }

        }

    }
}