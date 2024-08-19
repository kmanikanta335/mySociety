package com.society.model

data class Announcement(
    val id: String = "", // Firestore document ID
    val message: String = "",
    val timestamp: Long = System.currentTimeMillis()
)