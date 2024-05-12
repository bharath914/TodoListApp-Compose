package com.bharath.todolistapplication.domain.usecases

import com.bharath.todolistapplication.data.entity.TodoEntity
import com.bharath.todolistapplication.domain.repo.TodoRepository
import com.bharath.todolistapplication.ui.utils.TodoEntityWithDate
import com.bharath.todolistapplication.ui.utils.toTodoEntityWithDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject


/**
 * @property GetAllTodos gets all the todos from the database and maps them to TodoEntityWithDate
 * @property TodoEntityWithDate will be used for sorting the todos by date.
 */
class GetAllTodos @Inject constructor(
    private val todoRepository: TodoRepository,
) {
    suspend operator fun invoke(): Flow<List<TodoEntityWithDate>> =
        todoRepository.getAllTodos().filterNotNull().mapNotNull {
            it.map { entity ->
                entity.toTodoEntityWithDate()
            }
        }
}


/**
 * @property GetTodoById gets a todoEntity from the database
 */
class GetTodoById @Inject constructor(
    private val todoRepository: TodoRepository,
) {
    suspend operator fun invoke(id: Long): Flow<TodoEntity> = todoRepository.getTodoById(id)
}