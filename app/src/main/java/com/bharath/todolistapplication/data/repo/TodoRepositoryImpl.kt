package com.bharath.todolistapplication.data.repo

import com.bharath.todolistapplication.data.entity.TodoEntity
import com.bharath.todolistapplication.data.source.TodoDao
import com.bharath.todolistapplication.domain.repo.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val dao: TodoDao,
) : TodoRepository {
    override suspend fun upsertTodo(todo: TodoEntity) {
        withContext(Dispatchers.IO) {
            dao.upsertTodo(todo)
        }
    }

    override suspend fun deleteTodo(todo: TodoEntity) {
        withContext(Dispatchers.IO) {
            dao.deleteTodo(todo)
        }
    }

    override suspend fun getAllTodos(): Flow<List<TodoEntity>> {
        return withContext(Dispatchers.IO) {
            dao.getAllTodos()
        }
    }

    override suspend fun getTodoById(id: Long): Flow<TodoEntity> {
        return withContext(Dispatchers.IO) {
            dao.getTodoById(id)
        }
    }
}