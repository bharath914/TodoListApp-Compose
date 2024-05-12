package com.bharath.todolistapplication.presentation.addTodo.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

/**
 * Text box to input text
 * can be customized by the following params
 * @param modifier = Modifier to be applied to the text box
 * @param text = text to be displayed in the text box
 * @param indicatorColor = color of the indicator
 * @param placeHolder = placeholder text to display when text is empty.
 * @param style = style of the text in the text box
 * @param onTextChange = callback function to be called when text is changed
 */

@Composable
fun TextBox(
    modifier: Modifier = Modifier,
    text: String,
    indicatorColor: Color = MaterialTheme.colorScheme.surface,
    placeHolder: String = "Title Here...",
    style: TextStyle = MaterialTheme.typography.titleLarge,
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
            Text(text = placeHolder, style = style)
        },
        textStyle = style
    )

}