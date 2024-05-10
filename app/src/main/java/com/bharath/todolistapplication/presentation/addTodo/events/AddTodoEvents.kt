package com.bharath.todolistapplication.presentation.addTodo.events

sealed class AddTodoEvents {

    data class OnTitleEnter(val title: String) : AddTodoEvents()

    data class OnDescriptionEnter(val description: String) : AddTodoEvents()

    data class OnCompleteToggle(val isComplete: Boolean) : AddTodoEvents()

    data object OnSave : AddTodoEvents()

    data object OnBack : AddTodoEvents()


}