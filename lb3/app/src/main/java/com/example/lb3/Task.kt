package com.example.lb3

data class Task(
    val id: Int,
    var title: String,
    var description: String,
    var isCompleted: Boolean = false
)