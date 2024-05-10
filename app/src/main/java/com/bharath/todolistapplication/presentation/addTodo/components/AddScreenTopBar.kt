package com.bharath.todolistapplication.presentation.addTodo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
) {

    TopAppBar(title = { }, modifier = modifier, actions = {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            IconButton(onClick = onBackClick, Modifier.size(32.dp)) {
                Icon(
                    Icons.AutoMirrored.Sharp.ArrowBack,
                    contentDescription = "Back",
                    Modifier.size(32.dp)
                )
            }
            IconButton(onClick = onSaveClick, Modifier.size(32.dp)) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Save",
                    Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    })
}