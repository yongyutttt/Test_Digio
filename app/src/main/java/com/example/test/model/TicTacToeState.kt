package com.example.test.model

data class TicTacToeState(
    var board: List<List<CellState>> = List(3) { List(3) { CellState.EMPTY } },
    val currentCellState: CellState = CellState.X,
    val winner: CellState? = null,
    val showDialog: Boolean = false
)
