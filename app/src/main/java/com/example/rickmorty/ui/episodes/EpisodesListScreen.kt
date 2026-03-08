package com.example.rickmorty.ui.episodes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rickmorty.data.EpisodeDTO
import com.example.rickmorty.ui.state.UiState
import com.example.rickmorty.ui.theme.ColorCards
import com.example.rickmorty.ui.theme.ColorTitulos
import com.example.rickmorty.ui.theme.FondoApp
import com.example.rickmorty.R
import androidx.compose.foundation.shape.CircleShape
import com.example.rickmorty.ui.theme.ColorBordeCard
import com.example.rickmorty.ui.theme.ColorFondoChipInactivo
import com.example.rickmorty.ui.theme.ColorIconoInactivo
import com.example.rickmorty.ui.theme.ColorTextoChipInactivo
import com.example.rickmorty.ui.theme.ColorTextoInactivo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesListScreen() {

    val vm = remember { EpisodesListViewModel() }
    val state by vm.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        vm.load()
    }

    Scaffold(
        containerColor = FondoApp,
        topBar = {

            Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 48.dp, bottom = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                Text(
                    text = "Rick & Morty",
                    style = MaterialTheme.typography.headlineLarge,
                    color = ColorTitulos,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Directorio Dimension C-137",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                    }
                        Row {
                            IconButton(onClick = { /* No funcional */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_search),
                                    contentDescription = "Search",
                                    tint = ColorTitulos
                                )
                            }
                            IconButton(onClick = { /* No funcional */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_filter),
                                    contentDescription = "Filter",
                                    tint = ColorTitulos
                                )
                            }
                        }
                    }

                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Episodios",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                SeasonFilters()
            }
        },

        bottomBar = {
            Column {
                // Línea de BottomBar
                HorizontalDivider(
                    color = Color.White.copy(alpha = 0.2f),
                    thickness = 1.dp
                )

            NavigationBar(
                containerColor = FondoApp,
                tonalElevation = 8.dp

            ) {
                // Menú Episodios
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Navegación */ },
                    icon = { Icon(painterResource(R.drawable.ic_episodes), contentDescription = null) },
                    label = { Text("EPISODIOS", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                    colors = NavigationBarItemDefaults.colors(

                        selectedIconColor = ColorTitulos,
                        selectedTextColor = ColorTitulos,
                        indicatorColor = Color.Transparent,


                        unselectedIconColor = ColorIconoInactivo,
                        unselectedTextColor = ColorTextoInactivo
                    )
                )

                // Menú Personajes
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Navegación */ },
                    icon = { Icon(painterResource(R.drawable.ic_characters), contentDescription = null) },
                    label = { Text("PERSONAJES", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                    colors = NavigationBarItemDefaults.colors(

                            selectedIconColor = ColorTitulos,
                    selectedTextColor = ColorTitulos,
                    indicatorColor = Color.Transparent,


                    unselectedIconColor = ColorIconoInactivo,
                    unselectedTextColor = ColorTextoInactivo
                )
                )

                // Menú Información
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Navegación */ },
                    icon = { Icon(painterResource(R.drawable.ic_locations), contentDescription = null) },
                    label = { Text("WIKI", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                    colors = NavigationBarItemDefaults.colors(

                        selectedIconColor = ColorTitulos,
                        selectedTextColor = ColorTitulos,
                        indicatorColor = Color.Transparent,


                        unselectedIconColor = ColorIconoInactivo,
                        unselectedTextColor = ColorTextoInactivo
                    )
                )

                // Menú Favoritos
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Navegación */ },
                    icon = { Icon(painterResource(R.drawable.ic_watchlist), contentDescription = null) },
                    label = { Text("FAVORITOS", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                    colors = NavigationBarItemDefaults.colors(

                        selectedIconColor = ColorTitulos,
                        selectedTextColor = ColorTitulos,
                        indicatorColor = Color.Transparent,


                        unselectedIconColor = ColorIconoInactivo,
                        unselectedTextColor = ColorTextoInactivo
                    )
                )
            }
        }
        }

    ) { padding ->

        Column(
            Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val s = state) {
                UiState.Loading -> CircularProgressIndicator(color = ColorTitulos)
                is UiState.Error -> Text("Error: ${s.message}", color = Color.Red)
                is UiState.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(s.data) { episode: EpisodeDTO ->

                            EpisodeCard(episode = episode)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EpisodeCard(episode: EpisodeDTO) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 2.dp)
            .clickable { /* solo para el efecto */ },
        colors = CardDefaults.cardColors(
            containerColor = ColorCards
        ),
        shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                width = 1.dp,
        color = ColorBordeCard
    )
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = episode.episode.uppercase(),
                    color = ColorTitulos,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.5.sp
                )

                Spacer(modifier = Modifier.height(4.dp))


                Text(
                    text = episode.name,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = episode.air_date,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun SeasonFilters() {
    // Lista simple de temporadas
    val seasons = listOf("Temporada 1", "Temporada 2", "Temporada 3", "Temporada 4", "Temporada 5")

    // Estado Activo
    var selectedSeason by remember { mutableStateOf("Temporada 1") }

    LazyRow(
        modifier = Modifier.padding(
            top = 24.dp,
            bottom = 4.dp
        ),
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(seasons) { season ->
            val isSelected = season == selectedSeason


            Surface(
                onClick = { selectedSeason = season },

                color = if (isSelected) ColorTitulos else ColorFondoChipInactivo,
                shape = CircleShape,
                modifier = Modifier.height(40.dp),
                border = if (!isSelected) BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)) else null
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = season,
                        color = if (isSelected) FondoApp else ColorTextoChipInactivo,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}