package com.society.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.society.model.Event
import com.society.repository.EventsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventsViewModel(private val eventsRepository: EventsRepository) : ViewModel() {
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    init {
        fetchUpcomingEvents()
    }

    private fun fetchUpcomingEvents() {
        viewModelScope.launch {
            Log.d("EventsViewModel", "Fetching upcoming events")
            val eventsList = eventsRepository.getUpcomingEvents()
            Log.d("EventsViewModel", "Fetched ${eventsList.size} events")
            _events.value = eventsList
        }
    }
}

class EventsViewModelFactory(
    private val eventsRepository: EventsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventsViewModel(eventsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
