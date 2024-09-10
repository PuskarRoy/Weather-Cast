package com.example.weathercast.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.weathercast.model.Favourite
import com.example.weathercast.viewmodel.AppViewModel

@Composable
fun FavouriteScreen(navHostController: NavHostController, viewModel: AppViewModel) {

    val list = viewModel.FavList.collectAsState().value

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Favourite",
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (list.isEmpty()) {
                Text(
                    text = "Add to Favourite",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 50.dp)
                )
            } else {
                LazyColumn {
                    items(list) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            FavouriteRow(it, delete = { viewModel.deleteFavourite(it) }, onclick = {
                                viewModel.getWeather(it.city)
                                navHostController.popBackStack()
                            })
                        }

                    }
                }
            }

        }
    }
}


@Composable
private fun FavouriteRow(
    it: Favourite,
    delete: (Favourite) -> Unit = {},
    onclick: (Favourite) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(60.dp)
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onclick.invoke(it) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = it.city + " , " + it.country,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 20.dp)
            )

            IconButton(onClick = { delete.invoke(it) }, modifier = Modifier.padding(end = 5.dp)) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}