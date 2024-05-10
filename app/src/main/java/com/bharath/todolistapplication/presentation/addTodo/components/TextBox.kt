package com.bharath.todolistapplication.presentation.addTodo.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TextBox(
    modifier: Modifier = Modifier,
    text: String,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    placeHolder:String="Title Here...",
    onTextChange: (str: String) -> Unit,
) {

    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = indicatorColor,
            unfocusedIndicatorColor = indicatorColor,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(text = placeHolder)
        }
    )

}