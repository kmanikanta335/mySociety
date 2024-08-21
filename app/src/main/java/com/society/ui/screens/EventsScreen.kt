package com.society.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.society.WorkManager.createEventReminderNotificationChannel
import com.society.WorkManager.scheduleEventReminder
import com.society.model.Event
import com.society.repository.EventsRepository
import com.society.viewModel.EventsViewModel
import com.society.viewModel.EventsViewModelFactory
import java.util.concurrent.TimeUnit


@Composable
fun EventsScreen(eventsRepository: EventsRepository) {
    val viewModel: EventsViewModel = viewModel(
        factory = EventsViewModelFactory(eventsRepository)
    )
    val context = LocalContext.current
    val events by viewModel.events.collectAsState()
    // Create Notification Channel for Event Reminders
    LaunchedEffect(Unit) {
        createEventReminderNotificationChannel(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Upcoming Events",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp) ,
            color = Color.White
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(events) { event ->
                EventItem(event = event)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ScheduleEventReminder(event, context)
            }
        }
    }
}

@Composable
fun EventItem(event: Event) {
    val formattedDate = event.date?.toDate()?.toString() ?: "Unknown Date"
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "${event.name} - $formattedDate",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp),
            color = Color.White
        )
        Text(
            text = event.description,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
    }
}

@Composable
fun ScheduleEventReminder(event: Event, context: Context) {
    // Calculate the delay until the event start time
    val delay = (event.date?.toDate()?.time ?:System.currentTimeMillis() ) - System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1) // Reminder 1 hour before

    // Schedule the reminder if it's in the future
    if (delay > 0) {
        LaunchedEffect(event) {
            scheduleEventReminder(context, delay)
        }
    }
}