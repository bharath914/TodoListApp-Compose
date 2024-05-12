package com.bharath.todolistapplication.presentation.addTodo.events


/**
 * Events Screen to register all the UI events
 * This will reduce the amount of code we will write
 * and the no  of functions in the viewmodel.
 */
sealed class AddTodoEvents {

    /**
     * @property OnTitleEnter is called when the user enters the title
     */
    data class OnTitleEnter(val title: String) : AddTodoEvents()

    /**
     * @property OnDescriptionEnter is called when the user enters the description
     */
    data class OnDescriptionEnter(val description: String) : AddTodoEvents()

    /**
     * @property OnCompleteToggle is called when the user toggle the complete button
     */
    data class OnCompleteToggle(val isComplete: Boolean) : AddTodoEvents()

    /**
     * @property OnSave is called when the user clicks the save button
     */
    data object OnSave : AddTodoEvents()

    /**
     * @property OnBack is called when the user clicks the back button
     */
    data object OnBack : AddTodoEvents()


}