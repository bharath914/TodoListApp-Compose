package com.bharath.todolistapplication.presentation.home.events

import com.bharath.todolistapplication.data.entity.TodoEntity


sealed class HomeEvents {

    data class OnCompleteToggle(val todoEntity: TodoEntity, val completed: Boolean) : HomeEvents()

    data class OnDelete(val todoEntity: TodoEntity) : HomeEvents()

    data object ResetSnack : HomeEvents()

    data object OnUndoDelete : HomeEvents()


}