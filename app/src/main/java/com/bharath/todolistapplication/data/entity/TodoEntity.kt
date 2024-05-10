package com.bharath.todolistapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("TodoEntity")
data class TodoEntity(
    @PrimaryKey
    val id: Long = System.currentTimeMillis(),
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
)
