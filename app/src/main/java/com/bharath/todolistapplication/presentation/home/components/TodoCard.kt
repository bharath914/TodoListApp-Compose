package com.bharath.todolistapplication.presentation.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.bharath.todolistapplication.data.entity.TodoEntity
import com.bharath.todolistapplication.presentation.home.events.HomeEvents

@Composable
fun TodoCard(
    modifier: Modifier = Modifier,
    todoEntity: TodoEntity,
    onEvent: (event: HomeEvents) -> Unit,
    onClick: () -> Unit,
) {

    Card(
        modifier = modifier
            .alpha( if (todoEntity.isCompleted) 0.5f else 1f)
            .padding(16.dp), onClick = onClick
    ) {
        Row(modifier = modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {

            // First Will be status of _todo that is checkbox
            RadioButton(selected = todoEntity.isCompleted, onClick = {
                onEvent(HomeEvents.OnCompleteToggle(todoEntity, !todoEntity.isCompleted))

            }, modifier = Modifier.weight(1f))


            // second will be title of todoo

            Text(
                text = todoEntity.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(3f),
                maxLines = 1,
                textAlign = TextAlign.Start,
                textDecoration = if (todoEntity.isCompleted) TextDecoration.LineThrough else TextDecoration.None
            )
            // third will be delete button of todoo.
            IconButton(
                onClick = { onEvent(HomeEvents.OnDelete(todoEntity)) },
                modifier = Modifier.weight(1f)
            ) {
                Icon(imageVector = Icons.Filled.DeleteOutline, contentDescription = "Delete")
            }

        }
    }

}