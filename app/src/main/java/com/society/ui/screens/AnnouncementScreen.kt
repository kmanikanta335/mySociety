package com.society.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.society.model.Announcement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import com.society.repository.AnnouncementRepository
import com.society.viewModel.AnnouncementsViewModel
import com.society.viewModel.AnnouncementsViewModelFactory


@Composable
fun AnnouncementScreen(repository: AnnouncementRepository) {
    val viewModel: AnnouncementsViewModel = viewModel(
        factory = AnnouncementsViewModelFactory(repository)
    )
    val announcements by viewModel.announcements.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Announcements", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(announcements) { announcement ->
                AnnouncementItem(announcement)
            }
        }
    }
}

@Composable
fun AnnouncementItem(announcement: Announcement) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = announcement.message, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Posted on: ${java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault()).format(announcement.timestamp)}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}