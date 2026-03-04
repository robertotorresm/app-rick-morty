package com.example.rickmorty.ui.episodes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rickmorty.ui.fake.FakeEpisode
import com.example.rickmorty.ui.fake.fakeEpisodes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesListScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Episodes (Fake)") }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(fakeEpisodes) { episode: FakeEpisode ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        }
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = episode.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "${episode.code} · ${episode.airData}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }


            }
        }

    }
}