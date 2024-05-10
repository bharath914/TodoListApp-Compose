package com.bharath.todolistapplication.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.bharath.todolistapplication.data.entity.TodoEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {
    @Upsert
    suspend fun upsertTodo(todo: TodoEntity)

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

    @Query("SELECT * FROM TodoEntity ORDER BY id Desc")
    fun getAllTodos(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM TodoEntity WHERE id = :id")
    fun getTodoById(id: Long): Flow<TodoEntity>


}