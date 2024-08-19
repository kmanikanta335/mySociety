package com.society.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.society.repository.EventsRepository
import com.society.viewModel.EventsViewModel
import com.society.viewModel.EventsViewModelFactory


@Composable
fun EventsScreen(eventsRepository: EventsRepository) {
    val viewModel: EventsViewModel = viewModel(
        factory = EventsViewModelFactory(eventsRepository)
    )
    val events by viewModel.events.collectAsState()

    Column(Modifier.padding(16.dp)) {
        Text("Upcoming Events", style = MaterialTheme.typography.bodyLarge)
        LazyColumn {
            items(events) { event ->

                Text("${event.name} - ${event.date}")
                Text("${event.description}")
            }
        }
    }
}
