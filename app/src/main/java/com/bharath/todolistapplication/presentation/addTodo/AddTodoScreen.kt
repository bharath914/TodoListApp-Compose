package com.bharath.todolistapplication.presentation.addTodo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bharath.todolistapplication.presentation.addTodo.components.AddScreenTopBar
import com.bharath.todolistapplication.presentation.addTodo.components.SaveButton
import com.bharath.todolistapplication.presentation.addTodo.components.TextBox
import com.bharath.todolistapplication.presentation.addTodo.events.AddTodoEvents

@Composable
fun AddTodoScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<AddTodoViewModel>()
    ContentView(viewModel = viewModel, navHostController = navHostController)
}

@Composable
private fun ContentView(
    viewModel: AddTodoViewModel,
    navHostController: NavHostController,
) {

    val currentTodo by viewModel.currentTodo.collectAsStateWithLifecycle()
    val onEvent: (event: AddTodoEvents) -> Unit = remember {
        return@remember viewModel::onEvent
    }
    val modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()

    LaunchedEffect(key1 = true) {
        viewModel.loadTodo()
    }

    Scaffold(
        topBar = {
            AddScreenTopBar(modifier = modifier, onBackClick = {
                onEvent(AddTodoEvents.OnBack)
                navHostController.navigateUp()
            }, onSaveClick = {
                onEvent(AddTodoEvents.OnSave)
            })
        },
        bottomBar = {
            SaveButton(
                modifier,
                buttonText = if (currentTodo.isCompleted) "Mark as Incomplete" else "Mark as Completed"
            ) {
                val completed = !currentTodo.isCompleted
                onEvent(AddTodoEvents.OnCompleteToggle(completed))
            }
        }
    ) { innerPadding ->


        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            TextBox(text = currentTodo.title, modifier = modifier) {
                onEvent(AddTodoEvents.OnTitleEnter(it))
            }
            TextBox(text = currentTodo.description, modifier = modifier, indicatorColor = MaterialTheme.colorScheme.surface, placeHolder = "Description Here") {
                onEvent(AddTodoEvents.OnDescriptionEnter(it))
            }

        }
    }


}

