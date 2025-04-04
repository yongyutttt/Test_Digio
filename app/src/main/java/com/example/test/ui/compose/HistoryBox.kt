package com.example.test.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.R
import com.example.test.ui.theme.TicTacToeTheme

@Composable
fun HistoryBox(
    historyData: List<Pair<String, String>>?,
    onDismiss: (Boolean) -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(stringResource(id = R.string.history)) },
        text = {
            Column {
                historyData?.forEach {
                    Text("${it.first}: The winner is ${it.second}")
                }
            }
        },
        confirmButton = {
            Button(onClick = {onDismiss(false)}) {
                Text(stringResource(id = R.string.close))
            }
        }
    )
}

@Preview
@Composable
private fun GameOverDialogPreviewWinnerX() {
    TicTacToeTheme {
        HistoryBox(listOf()) { }
    }
}