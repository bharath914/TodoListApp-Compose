package com.bharath.todolistapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This entity will be transformed as a table in the database.
 *
 */

@Entity("TodoEntity")
data class TodoEntity(
    /**
     * This is the primary key of the table.
     * also can be used as a timestamp and used for sorting.
     */
    @PrimaryKey
    val id: Long = System.currentTimeMillis(),
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
)
