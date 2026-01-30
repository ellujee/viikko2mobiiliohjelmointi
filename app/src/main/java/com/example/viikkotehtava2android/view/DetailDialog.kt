package com.example.viikkotehtava2android.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikkotehtava2android.model.Task

@Composable
fun DetailDialog(
    task: Task,
    onDismiss: () -> Unit,
    onConfirm: (Task) -> Unit,
    onDelete: (Int) -> Unit
) {

    var editedTitle by remember { mutableStateOf(task.title) }
    var editedDescription by remember { mutableStateOf(task.description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Muokkaa tehtävää") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = editedTitle,
                    onValueChange = { editedTitle = it },
                    label = { Text("Otsikko") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = editedDescription,
                    onValueChange = { editedDescription = it },
                    label = { Text("Kuvaus") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {

            Button(
                onClick = {
                    onConfirm(task.copy(title = editedTitle, description = editedDescription))
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Tallenna")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDelete(task.id)
                onDismiss()
            }) {
                Text("Poista", color = MaterialTheme.colorScheme.error)
            }
        }
    )
}