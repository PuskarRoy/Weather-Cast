package com.example.weathercast.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weathercast.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navHostController: NavHostController, viewModel: AppViewModel = hiltViewModel()) {

    val key = rememberSaveable {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Search", style = MaterialTheme.typography.headlineMedium) },
            navigationIcon = {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 2.dp)
                            .size(30.dp)
                    )
                }
            },
            modifier = Modifier
                .padding(5.dp)
                .shadow(elevation = 10.dp, spotColor = Color.Gray),
            backgroundColor = Color.Cyan
        )

    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            OutlinedTextField(
                value = key.value,
                onValueChange = {
                    key.value = it
                },
                label = { Text(text = "search by name", fontSize = 17.sp) },
                textStyle = TextStyle(fontSize = 20.sp), modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp), shape = RoundedCornerShape(10.dp),
                singleLine = true,
                keyboardActions = KeyboardActions(onSearch = { })
            )


            Button(
                onClick = { if (key.value.isNotEmpty()){
                    viewModel.getWeather(key.value)
                    key.value = ""
                    navHostController.popBackStack()
                } },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(15.dp)
                    )
                    .padding(top = 5.dp)
            ) {

                Text(
                    text = "Show Weather",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

            }
        }
    }
}