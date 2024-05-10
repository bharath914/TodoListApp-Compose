package com.bharath.todolistapplication.presentation.addTodo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SaveButton(modifier: Modifier = Modifier, buttonText: String, onClick: () -> Unit) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Button(onClick = onClick) {
            Text(text = buttonText, style = MaterialTheme.typography.labelLarge)
        }
    }
}