package com.bharath.todolistapplication.presentation.addTodo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.todolistapplication.data.entity.TodoEntity
import com.bharath.todolistapplication.domain.usecases.DeleteTodo
import com.bharath.todolistapplication.domain.usecases.GetTodoById
import com.bharath.todolistapplication.domain.usecases.UpsertTodo
import com.bharath.todolistapplication.presentation.addTodo.events.AddTodoEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val getTodoById: GetTodoById,
    savedStateHandle: SavedStateHandle,
    private val upsertTodo: UpsertTodo,
    private val deleteTodo: DeleteTodo,
) : ViewModel() {


    private val _currentTodo = MutableStateFlow(TodoEntity())
    val currentTodo = _currentTodo.asStateFlow()

    private val todoId = MutableStateFlow(0L)


    init {
        savedStateHandle.get<String>("Id")?.let {
            todoId.value = it.toLong()
        }
    }

    fun loadTodo() {
        viewModelScope.launch {
            getTodoById(todoId.value).filterNotNull().first()?.let { entity ->
                _currentTodo.update { entity }
            }
        }
    }

    fun onEvent(events: AddTodoEvents) {
        when (events) {
            is AddTodoEvents.OnBack -> {
                if (currentTodo.value.title.isNotEmpty() || currentTodo.value.description.isNotEmpty()) {
                saveTodo()
                }
            }

            is AddTodoEvents.OnCompleteToggle -> {
                _currentTodo.update {
                    it.copy(isCompleted = events.isComplete)
                }
            }

            is AddTodoEvents.OnDescriptionEnter -> {
                _currentTodo.update {
                    it.copy(description = events.description)
                }
            }

            is AddTodoEvents.OnSave -> {
                if (currentTodo.value.title.isNotEmpty() || currentTodo.value.description.isNotEmpty()) {
                    saveTodo()
                }
            }

            is AddTodoEvents.OnTitleEnter -> {
                _currentTodo.update {
                    it.copy(title = events.title)
                }
            }
        }
    }

    fun saveTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            upsertTodo(currentTodo.value)
        }
    }


}