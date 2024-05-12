package com.bharath.todolistapplication.domain.usecases

import com.bharath.todolistapplication.data.entity.TodoEntity
import com.bharath.todolistapplication.domain.repo.TodoRepository
import javax.inject.Inject


/**
 * @property UpsertTodo upsert a todoEntity to the database.
 * This use case  pattern will be helpful for reducing code in the viewmodels.
 */
class UpsertTodo @Inject constructor(
    private val todoRepository: TodoRepository,
) {
    suspend operator fun invoke(todoEntity: TodoEntity) {
        todoRepository.upsertTodo(todoEntity)
    }
}

/**
 * @property DeleteTodo delete a todoEntity from the database.
 *
 */

class DeleteTodo @Inject constructor(
    private val todoRepository: TodoRepository,
) {
    suspend operator fun invoke(todoEntity: TodoEntity) {
        todoRepository.deleteTodo(todoEntity)
    }
}