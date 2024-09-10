package com.example.weathercast.screens

import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.weathercast.R
import com.example.weathercast.model.Favourite
import com.example.weathercast.navigation.AppScreen
import com.example.weathercast.viewmodel.AppViewModel
import java.text.SimpleDateFormat


@Composable
fun MainScreen(navHostController: NavHostController, viewModel: AppViewModel = hiltViewModel()) {

    val showdialog = remember {
        mutableStateOf(false)
    }

    if (viewModel.apidata.value.loading == false) {
        Scaffold(topBar = { WeatherAppBar(viewModel, navHostController, showdialog) }) {

            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it),
                    horizontalArrangement = Arrangement.End
                ) {
                    DropDownMenu(showdialog, navHostController)
                }


                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Date(viewModel)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        WindPressureHumidity(viewModel)

                        WeatherCircle(viewModel)

                        SunriseSunset(viewModel)
                    }

                    WeatherText(viewModel)

                    Text(
                        text = "FORECAST",
                        modifier = Modifier.padding(top = 15.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Forecast(
                        day = "Today",
                        image = "https:${viewModel.apidata.value.data?.forecast?.forecastday?.get(0)?.day?.condition?.icon}",
                        info = viewModel.apidata.value.data?.forecast?.forecastday?.get(0)?.day?.condition?.text.toString(),
                        temperature = viewModel.apidata.value.data?.forecast?.forecastday?.get(0)?.day?.maxtemp_c.toString()
                    )
                    Forecast(
                        day = "Tomorrow",
                        image = "https:${viewModel.apidata.value.data?.forecast?.forecastday?.get(1)?.day?.condition?.icon}",
                        info = viewModel.apidata.value.data?.forecast?.forecastday?.get(1)?.day?.condition?.text.toString(),
                        temperature = viewModel.apidata.value.data?.forecast?.forecastday?.get(1)?.day?.maxtemp_c.toString()
                    )
                    Forecast(
                        day = SimpleDateFormat("EEE").format(
                            (viewModel.apidata.value.data?.forecast?.forecastday?.get(
                                2
                            )?.date_epoch?.toLong())?.times(1000)
                        ),
                        image = "https:${viewModel.apidata.value.data?.forecast?.forecastday?.get(2)?.day?.condition?.icon}",
                        info = viewModel.apidata.value.data?.forecast?.forecastday?.get(2)?.day?.condition?.text.toString(),
                        temperature = viewModel.apidata.value.data?.forecast?.forecastday?.get(2)?.day?.maxtemp_c.toString()
                    )


                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CircularProgressIndicator()


        }
    }

}


@Composable
private fun Forecast(day: String, image: String, info: String, temperature: String) {

    Card(
        shape = RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp, bottomEnd = 50.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp),
        backgroundColor = if (day == "Today") Color.Cyan else MaterialTheme.colorScheme.inverseOnSurface
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = day, style = MaterialTheme.typography.headlineMedium)
            Image(
                painter = rememberAsyncImagePainter(image),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = info,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.width(100.dp)
            )
            Text(text = temperature, style = MaterialTheme.typography.titleMedium)
        }
    }

}

@Composable
private fun SunriseSunset(viewModel: AppViewModel) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .scale(0.98f)
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Sunrise", style = MaterialTheme.typography.titleLarge)
        Text(
            text = viewModel.apidata.value.data?.forecast?.forecastday?.get(0)?.astro?.sunrise.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text("Sunset", style = MaterialTheme.typography.titleLarge)
        Text(
            text = viewModel.apidata.value.data?.forecast?.forecastday?.get(0)?.astro?.sunset.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

    }
}

@Composable
private fun WindPressureHumidity(viewModel: AppViewModel) {
    Column(
        modifier = Modifier
            .padding(top = 15.dp)
            .scale(0.98f)
    ) {
        Row(modifier = Modifier.padding(vertical = 20.dp)) {
            Icon(
                painterResource(id = R.drawable.wind),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " ${viewModel.apidata.value.data?.current?.wind_mph?.toInt()} mph",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Row(modifier = Modifier.padding(vertical = 20.dp)) {
            Icon(
                painterResource(id = R.drawable.pressure),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " ${viewModel.apidata.value.data?.current?.pressure_mb?.toInt()} mb",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Row(modifier = Modifier.padding(vertical = 20.dp)) {
            Icon(
                painterResource(id = R.drawable.humidity),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " ${viewModel.apidata.value.data?.current?.humidity}%",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun Date(viewModel: AppViewModel) {
    Text(
        text = SimpleDateFormat("EEE, MMM d").format(
            viewModel.apidata.value.data?.current?.last_updated_epoch?.toLong()?.times(1000)
        ),
        modifier = Modifier.padding(top = 5.dp, bottom = 8.dp),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun WeatherText(viewModel: AppViewModel) {
    Card(
        backgroundColor = Color.Cyan,
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
        elevation = 5.dp
    ) {

        Column(
            modifier = Modifier.wrapContentSize(align = Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = viewModel.apidata.value.data?.current?.condition?.text.toString(),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(30.dp)
            )
        }

    }
}

@Composable
private fun WeatherCircle(viewModel: AppViewModel) {
    Card(
        shape = CircleShape,
        backgroundColor = Color.Cyan,
        modifier = Modifier.size(200.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = "https:${viewModel.apidata.value.data?.current?.condition?.icon}"),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = if (viewModel.unit.value == "c") viewModel.apidata.value.data?.current?.temp_c?.toInt()
                    .toString() + "°" else viewModel.apidata.value.data?.current?.temp_f?.toInt()
                    .toString() + "°",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold

            )


        }
    }
}

@Composable
private fun WeatherAppBar(
    viewModel: AppViewModel, navHostController: NavHostController, show: MutableState<Boolean>
) {
    val inFav = viewModel.FavList.collectAsState().value.filter { item ->
        (item.city == viewModel.apidata.value.data?.location?.name.toString())
    }
    val context = LocalContext.current

    TopAppBar(title = {
        Text(
            text = viewModel.apidata.value.data?.location?.name + "," + viewModel.apidata.value.data?.location?.country,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
    }, actions = {
        if (inFav.isEmpty()) {
            IconButton(onClick = {
                viewModel.insertFavourite(
                    Favourite(
                        city = viewModel.apidata.value.data?.location?.name.toString(),
                        country = viewModel.apidata.value.data?.location?.country.toString()
                    )
                )
                Toast.makeText(context, "Added to Favourite", Toast.LENGTH_LONG).show()

            }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }

        IconButton(onClick = {
            navHostController.navigate(AppScreen.SearchScreen.route)
        }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
        IconButton(onClick = {
            show.value = !show.value
        }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
        }

    },

        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp, top = 5.dp)
            .wrapContentHeight()
            .shadow(elevation = 80.dp, spotColor = Color.Gray), backgroundColor = Color.Cyan


    )


}

@Composable
private fun DropDownMenu(show: MutableState<Boolean>, navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 5.dp)
    ) {
        DropdownMenu(expanded = show.value, onDismissRequest = { show.value = false }) {
            DropdownMenuItem(text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        Modifier.padding(end = 5.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Setting",
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(end = 5.dp)
                    )
                }
            }, onClick = {
                show.value = false
                navHostController.navigate(AppScreen.SettingScreen.route)
            })


            DropdownMenuItem(text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        Modifier.padding(end = 5.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Favourite",
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(end = 5.dp)
                    )
                }
            }, onClick = {
                show.value = false
                navHostController.navigate(AppScreen.FavoriteScreen.route)
            })
            DropdownMenuItem(text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        Modifier.padding(end = 5.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "About",
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(end = 5.dp)
                    )
                }
            }, onClick = {
                show.value = false
                navHostController.navigate(AppScreen.AboutScreen.route)
            })

        }
    }
}
