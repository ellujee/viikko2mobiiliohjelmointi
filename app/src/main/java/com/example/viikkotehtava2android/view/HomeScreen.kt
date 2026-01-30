package com.example.viikkotehtava2android.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkotehtava2android.viewmodel.TaskViewModel
import com.example.viikkotehtava2android.model.Task

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = viewModel()
) {
    val taskList = viewModel.tasks
    var newTaskTitle by remember { mutableStateOf("") }


    viewModel.editingTask?.let { task ->
        DetailDialog(
            task = task,
            onDismiss = { viewModel.dismissDialog() },
            onConfirm = { updatedTask -> viewModel.updateTask(updatedTask) },
            onDelete = { id -> viewModel.removeTask(id) }
        )
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Tehtävälista", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))


        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                modifier = Modifier.weight(1f),
                label = { Text("Uusi tehtävä") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (newTaskTitle.isNotBlank()) {
                    val nextId = (taskList.maxOfOrNull { it.id } ?: 0) + 1
                    viewModel.addTask(Task(nextId, newTaskTitle, "", 1, "2026-01-01", false))
                    newTaskTitle = ""
                }
            }) {
                Text("Lisää")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { viewModel.sortByDueDate() }, modifier = Modifier.weight(1f)) {
                Text("Järjestä", style = MaterialTheme.typography.bodySmall)
            }
            Button(onClick = { viewModel.filterByDone(done = true) }, modifier = Modifier.weight(1f)) {
                Text("Vain valmiit", style = MaterialTheme.typography.bodySmall)
            }
            Button(onClick = { viewModel.resetFilter() }, modifier = Modifier.weight(1f)) {
                Text("Näytä kaikki", style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(taskList) { task ->
                TaskRow(
                    task = task,
                    onToggle = { viewModel.toggleDone(task.id) },
                    onDelete = { viewModel.removeTask(task.id) },
                    onClick = { viewModel.editTask(task) } // Avaa dialogin
                )
            }
        }
    }
}

@Composable
fun TaskRow(task: Task, onToggle: () -> Unit, onDelete: () -> Unit, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = task.done, onCheckedChange = { onToggle() })
            Text(
                text = task.title,
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Poista",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}