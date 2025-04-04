package com.example.test.viewModel.manager

import com.example.test.model.CellState
import com.example.test.model.Player

interface ICheckWinnerManager {
    fun checkWinner(board: List<List<CellState>>, winningLength: Int): Player?
}