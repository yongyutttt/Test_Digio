package com.example.test.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

import com.example.test.R
import com.example.test.model.CellState
import com.example.test.model.TicTacToeState
import com.example.test.ui.theme.TicTacToeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicTacToeGame(
    state: TicTacToeState,
    historyData: List<Pair<String, String>>?,
    resetGame: () -> Unit,
    enterSize: (String) -> Unit,
    onCellClicked: (Int, Int) -> Unit,
    history: () -> Unit
) {
    var showBox by remember { mutableStateOf(true) }
    var showHistory by remember { mutableStateOf(false) }

    if (state.showDialog) {
        history.invoke()
        GameOverDialog(winner = state.winner, onDismiss = resetGame)
    }
    if (showBox){
        GameSize(
            title = "Tic Tac Toe",
            message = "Please enter size to play game",
            onConfirm = { size, show ->
                showBox = show
                enterSize.invoke(size)
            },
            showHistory = {
                showHistory = it
            }
        )
    }
    if(showHistory){
        HistoryBox(
            historyData
        ) {
            showHistory = it
        }
    }


    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(id = R.string.app_title)) })
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            GameBoard(board = state.board, onCellClicked = onCellClicked)
        }
    }
}

@Preview
@Composable
private fun TicTacToeGamePreview() {
    val mockBoard = List(3) { rowIndex ->
    List(3) { columnIndex ->
        when ((rowIndex + columnIndex) % 2) {
            0 -> CellState.X
            else -> CellState.O
        }
    }
}
    TicTacToeTheme {
        TicTacToeGame(
            state = TicTacToeState(board = mockBoard),
            historyData = listOf(),
            resetGame = { },
            enterSize= {},
            onCellClicked = { _, _ -> },
            history = {}
        )
    }
}

@Preview
@Composable
private fun TicTacToeGameGameOverDrawPreview() {
    TicTacToeTheme {
        TicTacToeGame(
            state = TicTacToeState(showDialog = true),
            historyData = listOf(),
            resetGame = { },
            enterSize= {},
            onCellClicked = { _, _ -> },
            history = {}
        )
    }
}

@Preview
@Composable
private fun TicTacToeGameGameOverXWinPreview() {
    TicTacToeTheme {
        TicTacToeGame(
            state = TicTacToeState(showDialog = true, winner = CellState.X),
            historyData = listOf(),
            resetGame = { },
            enterSize= {},
            onCellClicked = { _, _ -> },
            history = {}
        )
    }
}

@Preview
@Composable
private fun TicTacToeGameGameOverOWinPreview() {
    TicTacToeTheme {
        TicTacToeGame(
            state = TicTacToeState(showDialog = true, winner = CellState.O),
            historyData = listOf(),
            resetGame = { },
            enterSize = {},
            onCellClicked = { _, _ -> },
            history = {}
        )
    }
}
