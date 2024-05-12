package com.bharath.todolistapplication.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.bharath.todolistapplication.data.entity.TodoEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {
    /**
     * Upsert todoEntity. If the todoEntity already exists, it will be updated.
     */
    @Upsert
    suspend fun upsertTodo(todo: TodoEntity)

    /**
     * Delete todoEntity.
     */
    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

    /**
     * Get all todos. Order by id in descending order.
     */

    @Query("SELECT * FROM TodoEntity ORDER BY id Desc")
    fun getAllTodos(): Flow<List<TodoEntity>>

    /**
     * Get todoEntity by id.
     */
    @Query("SELECT * FROM TodoEntity WHERE id = :id")
    fun getTodoById(id: Long): Flow<TodoEntity>


}