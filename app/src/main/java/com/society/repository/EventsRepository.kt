package com.society.repository

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.firestore.FirebaseFirestore
import com.society.model.Event
import kotlinx.coroutines.tasks.await

class EventsRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getUpcomingEvents(): List<Event> {
        return try {
            val result = db.collection("events").get().await()
            Log.d("EventsRepository", "Fetched ${result.size()} events from Firestore")
            result.forEach { document -> Log.d("EventsRepository", "Event: ${document.data}") }

            result.map { document ->
                document.toObject(Event::class.java).copy(id = document.id)
            }.filter { event ->
                 print(event.name)

                event.isUpcoming // filter all the events which are isUpcoming = true
            }
        } catch (e: Exception) {
            Log.d("NO storage ",""+e)
            emptyList() // return the empty list
        }
    }
}