package com.example.test.model

typealias Player = CellState

enum class CellState {
    X, O, EMPTY;

    fun toggle(): CellState = if (this == X) O else X
}
val CellState.description: String
    get() = when (this) {
        CellState.X -> "X"
        CellState.O -> "O"
        CellState.EMPTY -> ""
    }