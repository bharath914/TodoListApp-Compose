package com.bharath.todolistapplication.domain.repo

import com.bharath.todolistapplication.data.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

/**
 * This todoRepository will be to implemented by the data layer
 * This methodology will be helpful in creating testcases.
 */

interface TodoRepository {

    /**
     * All Methods that are in the TodoRepository will be implemented by the data layer
     */
    suspend fun upsertTodo(todo: TodoEntity)

    suspend fun deleteTodo(todo: TodoEntity)

    suspend fun getAllTodos(): Flow<List<TodoEntity>>

    suspend fun getTodoById(id: Long): Flow<TodoEntity>
}