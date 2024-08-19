package com.society.model

data class Event(
    val id: String = "",
    val name: String = "",
    val date: String = "",
    val description: String = "",
    val isUpcoming: Boolean = true // Boolean to indicate if the event is upcoming
)
