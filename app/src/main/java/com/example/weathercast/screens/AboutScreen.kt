package com.example.weathercast.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.weathercast.R

@Composable
fun AboutScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "About",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                fontSize = 23.sp
            )
        }, navigationIcon = {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null, modifier = Modifier.size(30.dp)
                )

            }
        },

            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp, top = 5.dp)
                .wrapContentHeight()
                .shadow(elevation = 80.dp, spotColor = Color.Gray), backgroundColor = Color.Cyan


        )

    }) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_version),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(vertical = 5.dp)
            )
            Text(
                text = stringResource(id = R.string.api_use),
                style = MaterialTheme.typography.titleLarge
            )
        }

    }
}