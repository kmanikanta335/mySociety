package com.society.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.society.model.Announcement
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AnnouncementRepository {

    private val db = FirebaseDatabase.getInstance().getReference("announcements")

    fun getAnnouncements(): Flow<List<Announcement>> = callbackFlow {
        val announcementsListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val announcements = snapshot.children.map { dataSnapshot ->
                    Announcement(
                        id = dataSnapshot.key ?: "",
                        message = dataSnapshot.child("message").getValue(String::class.java) ?: "",
                        timestamp = dataSnapshot.child("timestamp").getValue(Long::class.java) ?: System.currentTimeMillis()
                    )
                }
                trySend(announcements).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException()) // Close the flow if there's an error
            }
        }

        db.addValueEventListener(announcementsListener) // Attach listener to Firebase Database

        awaitClose { db.removeEventListener(announcementsListener) } // Clean up listener on close
    }
}