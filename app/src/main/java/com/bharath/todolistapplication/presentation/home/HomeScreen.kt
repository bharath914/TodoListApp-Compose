package com.bharath.todolistapplication.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bharath.todolistapplication.presentation.home.components.TodoCard
import com.bharath.todolistapplication.presentation.home.events.HomeEvents
import com.bharath.todolistapplication.ui.navigation.Screens
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navHostController: NavHostController) {

    val viewModel = hiltViewModel<HomeViewModel>()
    ContentView(viewModel = viewModel, navHostController = navHostController)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContentView(
    viewModel: HomeViewModel,
    navHostController: NavHostController,
) {
    /**
     * Single Function to trigger All UI events in this screen.
     */

    val onEvent: (event: HomeEvents) -> Unit = remember {
        return@remember viewModel::onEvent
    }
    val modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth()

    val snackBarState = remember {
        SnackbarHostState()
    }
    val snackBarData by viewModel.snackBarData.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.getTodos()
    }

    /**
     * Launched Effect block to be triggered when we want to show the snackbar.
     */
    LaunchedEffect(key1 = snackBarData) {
        launch {


            if (snackBarData.showSnack) {
                val snackResult = if (snackBarData.isDeleteSnack) {
                    snackBarState.showSnackbar(
                        snackBarData.message!!,
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Short
                    )
                } else {
                    snackBarState.showSnackbar(
                        snackBarData.message!!,
                        duration = SnackbarDuration.Short
                    )
                }
                when (snackResult) {
                    SnackbarResult.Dismissed -> {
                        onEvent(HomeEvents.ResetSnack)
                    }

                    SnackbarResult.ActionPerformed -> {
                        onEvent(HomeEvents.OnUndoDelete)
                    }

                    else -> {
                        onEvent(HomeEvents.ResetSnack)
                    }
                }

            }
        }.invokeOnCompletion {
            onEvent(HomeEvents.ResetSnack)
        }
    }
    /**
     * List of the Todos and will be collected as state from the viewmodel with the awareness of lifecycle
     */

    val list by viewModel.todosList.collectAsStateWithLifecycle()


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarState) {
                Snackbar(snackbarData = it)
            }
        },
        topBar = {
            Column {
                /**
                 * Topbar in the homescreen.
                 */
                TopAppBar(title = { Text(text = "Home") })
                HorizontalDivider(
                    modifier = Modifier
                        .offset(y = (-8).dp)
                        .fillMaxWidth()
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navHostController.navigate(Screens.AddNote.route + "/0")
            }) {
                Icon(
                    imageVector = Icons.Filled.Add, contentDescription = "Add"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            list.forEach {
                item {

                    /**
                     * Header to Show the date
                     */
                    Text(
                        text = it.key,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                /**
                 * List of items in that date.
                 */

                items(it.value, key = { todoEntityWithDate ->
                    todoEntityWithDate.todoEntity.id
                }) { entity ->
                    TodoCard(modifier, todoEntity = entity.todoEntity, onEvent = onEvent) {
                        navHostController.navigate(Screens.AddNote.route + "/${entity.todoEntity.id}")
                    }
                }
            }
        }
    }

}