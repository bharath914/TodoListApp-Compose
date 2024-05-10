package com.bharath.todolistapplication.domain.repo

import com.bharath.todolistapplication.data.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun upsertTodo(todo: TodoEntity)

    suspend fun deleteTodo(todo: TodoEntity)

    suspend fun getAllTodos(): Flow<List<TodoEntity>>

    suspend fun getTodoById(id: Long): Flow<TodoEntity>
}