package com.bharath.todolistapplication.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bharath.todolistapplication.data.entity.TodoEntity

/**
 * Database schema that holds the list of tasks.
 *
 */

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao
}
