package com.example.test.viewModel.manager

import com.example.test.model.CellState

interface IMoveManager {
    fun makeMove(board: List<List<CellState>>, onMoveMade: (Int, Int) -> Unit)
}
