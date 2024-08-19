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
                // Assuming you have a date parsing logic to determine if it's an upcoming event
                event.isUpcoming // You can replace this with your date logic
            }
        } catch (e: Exception) {
            Log.d("NO storage ",""+e)
            emptyList() // Handle the error accordingly
        }
    }
}