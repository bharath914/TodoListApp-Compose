package com.bharath.todolistapplication.ui.utils

import com.bharath.todolistapplication.data.entity.TodoEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class TodoEntityWithDate(
    val todoEntity: TodoEntity,
    val day: String,
)

fun TodoEntity.toTodoEntityWithDate(): TodoEntityWithDate {

    val day = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(this.id)
    val formatted = getFormattedDate(day)
    return TodoEntityWithDate(this, formatted)

}

private fun getFormattedDate(date: String): String {
    val current =
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(System.currentTimeMillis())
    val yesterday = Calendar.getInstance()
    yesterday.add(Calendar.DATE, -1)
    return when (date) {
        current -> "Today"
        else -> date
    }
}