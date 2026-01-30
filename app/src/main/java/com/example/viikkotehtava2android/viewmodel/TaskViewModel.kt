package com.example.viikkotehtava2android.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viikkotehtava2android.model.Task
import com.example.viikkotehtava2android.model.mockTasks

class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf(listOf<Task>())
        private set


    var editingTask by mutableStateOf<Task?>(null)
        private set

    init {
        tasks = mockTasks
    }

    fun addTask(task: Task) {
        tasks = tasks + task
    }

    fun toggleDone(id: Int) {
        tasks = tasks.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
    }

    fun removeTask(id: Int) {
        tasks = tasks.filter { it.id != id }
    }

    fun updateTask(updatedTask: Task) {
        tasks = tasks.map {
            if (it.id == updatedTask.id) updatedTask else it
        }
        dismissDialog()
    }

    fun editTask(task: Task) {
        editingTask = task
    }

    fun dismissDialog() {
        editingTask = null
    }

    fun sortByDueDate() {
        tasks = tasks.sortedBy { it.dueDate }
    }

    fun filterByDone(done: Boolean) {
        tasks = tasks.filter { it.done == done }
    }

    fun resetFilter() {
        tasks = mockTasks
    }
}