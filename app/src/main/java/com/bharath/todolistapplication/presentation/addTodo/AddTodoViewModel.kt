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

/**
 *@property  AddTodoViewModel => Viewmodel to handle UI Tasks of AddTodo Screen
 */
@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val getTodoById: GetTodoById,
    savedStateHandle: SavedStateHandle,
    private val upsertTodo: UpsertTodo
) : ViewModel() {

    /**
     * _currentTodo holds the state of the current todoEntity (title, description, isCompleted).
     */

    private val _currentTodo = MutableStateFlow(TodoEntity())
    val currentTodo = _currentTodo.asStateFlow()


    private val todoId = MutableStateFlow(0L)


    init {
        /**
         * Get the passed argument from the HomeScreen
         */
        savedStateHandle.get<String>("Id")?.let {
            todoId.value = it.toLong()
        }
    }

    fun loadTodo() {
        /**
         * Loads the data from the Database
         * If it's new note then nothing happens in this function. as we use filterNotNull() function here.
         */
        viewModelScope.launch {
            getTodoById(todoId.value).filterNotNull().first()?.let { entity ->
                _currentTodo.update { entity }
            }
        }
    }

    /**
     * onEvent() public function to be exposed to UI Screens to talk with the viewmodel
     * This will reduce the amount of functions in the viewmodel and logic in UI.
     */
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

    private fun saveTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            upsertTodo(currentTodo.value)
        }
    }


}