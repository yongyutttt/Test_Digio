package com.example.test.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.ui.theme.TicTacToeTheme

@Composable
fun GameSize(
    title: String,
    message: String,
    onConfirm: (String, Boolean) -> Unit,
    showHistory: (Boolean) -> Unit
) {
    var text by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = {},
        title = { Text(title) },
        text = {
            Column {
                Text(message)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("size") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onConfirm(text, false)

            }) {
                Text("OK")
            }

        },
        dismissButton = {
            Button(onClick = {showHistory(true)}) {
                Text("History")
            }
        }
    )
}

@Preview
@Composable
private fun InputDialogPreview(){
    TicTacToeTheme {
        GameSize(
            "Enter Name",
            "Please enter your name below:",
            onConfirm = { _, _ -> },
            showHistory = {}
        )
    }
}