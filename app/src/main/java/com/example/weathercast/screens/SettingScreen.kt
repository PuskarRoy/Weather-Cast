package com.example.weathercast.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.weathercast.viewmodel.AppViewModel

@Composable
fun SettingScreen(navHostController: NavHostController, viewModel: AppViewModel) {

    val expand = rememberSaveable {
        mutableStateOf(false)
    }
    val unittext = rememberSaveable {
        mutableStateOf(if (viewModel.unit.value == "c") "°Celsius" else "°Fahrenheit")
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Setting",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                fontSize = 23.sp
            )
        }, navigationIcon = {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
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

            Text(text = "Change Temperature Unit", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(20.dp))
            Box {
                Button(
                    onClick = { expand.value = true },
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                    elevation = ButtonDefaults.elevation(100.dp)
                ) {
                    Text(text = unittext.value, style = MaterialTheme.typography.titleLarge)
                }
                DropdownMenu(
                    expanded = expand.value,
                    onDismissRequest = { expand.value = !expand.value },
                    modifier = Modifier
                        .width(150.dp)
                        .wrapContentHeight()
                ) {
                    DropdownMenuItem(text = {
                        Text(
                            text = "°Celsius", style = MaterialTheme.typography.titleLarge
                        )
                    }, onClick = {
                        unittext.value = "°Celsius"
                        expand.value = false
                    })

                    DropdownMenuItem(text = {
                        Text(
                            text = "°Fahrenheit", style = MaterialTheme.typography.titleLarge
                        )
                    }, onClick = {
                        unittext.value = "°Fahrenheit"
                        expand.value = false
                    })


                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (unittext.value == "°Fahrenheit") {
                        viewModel.unit.value = "f"
                    } else {
                        viewModel.unit.value = "c"
                    }

                    navHostController.popBackStack()

                },
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
            ) {
                Text(text = "save", style = MaterialTheme.typography.titleLarge)
            }

        }
    }
}