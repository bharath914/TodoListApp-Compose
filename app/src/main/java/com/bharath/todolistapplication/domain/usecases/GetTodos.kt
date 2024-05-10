package com.bharath.todolistapplication.domain.usecases

import com.bharath.todolistapplication.data.entity.TodoEntity
import com.bharath.todolistapplication.domain.repo.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllTodos @Inject constructor(
    private val todoRepository: TodoRepository,
) {
    suspend operator fun invoke(): Flow<List<TodoEntity>> = todoRepository.getAllTodos()
}

class GetTodoById @Inject constructor(
    private val todoRepository: TodoRepository,
) {
    suspend operator fun invoke(id: Long): Flow<TodoEntity> = todoRepository.getTodoById(id)
}