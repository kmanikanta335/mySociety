package com.society.model

import com.google.firebase.Timestamp


data class Event(
    val id: String = "",
    val name: String = "",
    val date: Timestamp? = null,
    val description: String = "",
    val isUpcoming: Boolean = true // Boolean to indicate if the event is upcoming
)
