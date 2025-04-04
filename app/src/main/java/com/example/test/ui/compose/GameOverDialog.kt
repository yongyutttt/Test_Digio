package com.example.test.ui.compose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.R
import com.example.test.model.Player
import com.example.test.model.description
import com.example.test.ui.theme.TicTacToeTheme

@Composable
fun GameOverDialog(winner: Player?, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(id = R.string.game_over)) },
        text = {
            Text(
                if (winner != null) stringResource(
                    id = R.string.winner,
                    winner.description
                ) else stringResource(id = R.string.draw)
            )
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(stringResource(id = R.string.restart))
            }
        }
    )
}

@Preview
@Composable
private fun GameOverDialogPreviewWinnerX() {
    TicTacToeTheme {
        GameOverDialog(winner = Player.X) { }
    }
}

@Preview
@Composable
private fun GameOverDialogPreviewWinnerO() {
    TicTacToeTheme {
        GameOverDialog(winner = Player.O) { }
    }
}

@Preview
@Composable
private fun GameOverDialogPreviewDraw() {
    TicTacToeTheme {
        GameOverDialog(winner = null) { }
    }
}