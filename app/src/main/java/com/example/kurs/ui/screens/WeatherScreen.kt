import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kurs.ui.screens.components.CityWeatherCard
import com.example.kurs.ui.screens.components.EmptyCityList
import com.example.kurs.ui.screens.components.WeatherSearchBar
import com.example.kurs.viewmodel.WeatherViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.res.stringResource
import com.example.kurs.R
import com.example.kurs.navigation.NavigationRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    navController: NavController = rememberNavController(),
    viewModel: WeatherViewModel = viewModel()
) {
    val cities by viewModel.cities.collectAsState()
    val newCityInput by viewModel.newCityInput.collectAsState()
    val suggestions by viewModel.suggestions.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    var searchJob by remember { mutableStateOf<Job?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.my_cities),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                actions = {
                    IconButton(onClick = { viewModel.refreshAllCities() }) {
                        Icon(Icons.Default.Refresh, contentDescription = stringResource(R.string.cd_refresh))
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {

            WeatherSearchBar(
                inputText = newCityInput,
                onInputChange = { query ->
                    viewModel.updateNewCityInput(query)
                    searchJob?.cancel()
                    if (query.length >= 2) {
                        searchJob = coroutineScope.launch {
                            delay(500)
                            viewModel.searchCities(query)
                        }
                    } else {
                        viewModel.clearSuggestions()
                    }
                },
                suggestions = suggestions,
                isSearching = isSearching,
                onSuggestionClick = { suggestion ->
                    viewModel.addCity(suggestion.name)
                    viewModel.updateNewCityInput("")
                    viewModel.clearSuggestions()
                },
                onClear = {
                    viewModel.updateNewCityInput("")
                    viewModel.clearSuggestions()
                }
            )

            if (cities.isEmpty()) {
                EmptyCityList()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.spacing_horizontal_list), vertical = dimensionResource(R.dimen.padding_small)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_vertical_list))
                ) {
                    items(cities) { city ->
                        CityWeatherCard(
                            city = city,
                            onRefresh = { viewModel.refreshWeather(city.cityInfo.name) },
                            onRemove = { viewModel.removeCity(city.cityInfo.name) },
                            onCityClick = {
                                navController.navigate(NavigationRoutes.weeklyForecast(city.cityInfo.name))
                            }
                        )
                    }
                }
            }
        }
    }
}