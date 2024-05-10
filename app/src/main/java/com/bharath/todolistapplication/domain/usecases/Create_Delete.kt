package com.bharath.todolistapplication.domain.usecases

import com.bharath.todolistapplication.data.entity.TodoEntity
import com.bharath.todolistapplication.domain.repo.TodoRepository
import javax.inject.Inject

class UpsertTodo @Inject constructor(
    private val todoRepository: TodoRepository,
) {
    suspend operator fun invoke(todoEntity: TodoEntity) {
        todoRepository.upsertTodo(todoEntity)
    }
}

class DeleteTodo @Inject constructor(
    private val todoRepository: TodoRepository,
) {
    suspend operator fun invoke(todoEntity: TodoEntity) {
        todoRepository.deleteTodo(todoEntity)
    }
}