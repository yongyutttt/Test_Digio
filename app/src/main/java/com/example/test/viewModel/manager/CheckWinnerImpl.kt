package com.example.test.viewModel.manager

import com.example.test.model.CellState
import com.example.test.model.Player

object CheckWinnerImpl: ICheckWinnerManager {
    override fun checkWinner(board: List<List<CellState>>, winningLength: Int): Player? {
        val size = board.size

        // Check horizontal lines
        for (row in board) {
            if (checkConsecutive(row, winningLength)) {
                return row.first { it != CellState.EMPTY }
            }
        }

        // Check vertical lines
        for (col in 0 until size) {
            val column = List(size) { row -> board[row][col] }
            if (checkConsecutive(column, winningLength)) {
                return column.first { it != CellState.EMPTY }
            }
        }

        // Check diagonals
        val diagonals = getAllDiagonals(board, winningLength)
        for (diag in diagonals) {
            if (checkConsecutive(diag, winningLength)) {
                return diag.first { it != CellState.EMPTY }
            }
        }

        return null // No winner
    }
}

    private fun checkConsecutive(cells: List<CellState>, length: Int): Boolean {
        var count = 1
        for (i in 1 until cells.size) {
            if (cells[i] != CellState.EMPTY && cells[i] == cells[i - 1]) {
                count++
                if (count == length) return true
            } else {
                count = 1
            }
        }
        return false
    }

    private fun getAllDiagonals(board: List<List<CellState>>, winningLength: Int): List<List<CellState>> {
        val size = board.size
        val diagonals = mutableListOf<List<CellState>>()

        // Get diagonals from top-left to bottom-right
        for (d in -size + 1 until size) {
            val diag = mutableListOf<CellState>()
            for (i in 0 until size) {
                val j = i + d
                if (j in 0 until size) {
                    diag.add(board[i][j])
                }
            }
            if (diag.size >= winningLength) diagonals.add(diag)
        }

        // Get diagonals from top-right to bottom-left
        for (d in -size + 1 until size) {
            val diag = mutableListOf<CellState>()
            for (i in 0 until size) {
                val j = size - 1 - i + d
                if (j in 0 until size) {
                    diag.add(board[i][j])
                }
            }
            if (diag.size >= winningLength) diagonals.add(diag)
        }

        return diagonals
    }

