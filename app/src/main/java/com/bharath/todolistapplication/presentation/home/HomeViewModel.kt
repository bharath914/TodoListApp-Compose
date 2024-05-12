package com.bharath.todolistapplication.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.todolistapplication.data.entity.TodoEntity
import com.bharath.todolistapplication.domain.usecases.DeleteTodo
import com.bharath.todolistapplication.domain.usecases.GetAllTodos
import com.bharath.todolistapplication.domain.usecases.UpsertTodo
import com.bharath.todolistapplication.presentation.home.events.HomeEvents
import com.bharath.todolistapplication.ui.utils.SnackBarData
import com.bharath.todolistapplication.ui.utils.TodoEntityWithDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTodos: GetAllTodos,
    private val deleteTodo: DeleteTodo,
    private val upsertTodo: UpsertTodo,

    ) : ViewModel() {

    private val _todosList = MutableStateFlow(emptyMap<String, List<TodoEntityWithDate>>())
    val todosList = _todosList.asStateFlow()

    private val _snackBarData = MutableStateFlow(SnackBarData())
    val snackBarData = _snackBarData.asStateFlow()

    private val tempTodo = MutableStateFlow(TodoEntity())

    fun getTodos() {
        viewModelScope.launch(IO) {
            getAllTodos().collectLatest { list ->
                val group = list.groupBy { it.day }
                _todosList.update { group }
            }
        }
    }

    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.OnCompleteToggle -> {
                viewModelScope.launch(IO) {
                    upsertTodo(event.todoEntity.copy(isCompleted = event.completed))
                }
            }

            is HomeEvents.OnDelete -> {
                _snackBarData.update {
                    SnackBarData()
                }
                viewModelScope.launch(IO) {
                    deleteTodo(event.todoEntity)
                }.invokeOnCompletion {
                    tempTodo.value = event.todoEntity
                    _snackBarData.update {
                        SnackBarData(
                            "Todo Deleted Successfully",
                            true,
                            isDeleteSnack = true
                        )
                    }
                }
            }

            is HomeEvents.OnUndoDelete -> {
                viewModelScope.launch(IO) {
                    upsertTodo(tempTodo.value)
                }.invokeOnCompletion {
                    _snackBarData.update {
                        SnackBarData(
                            "Todo Restored Successfully",
                            true
                        )
                    }
                    tempTodo.value = TodoEntity()
                }
            }

            is HomeEvents.ResetSnack -> {
                _snackBarData.update {
                    SnackBarData()
                }
            }
        }

    }
}