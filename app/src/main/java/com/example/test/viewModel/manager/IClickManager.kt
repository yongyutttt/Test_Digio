package com.example.test.viewModel.manager

import com.example.test.model.TicTacToeState

interface IClickManager {

    fun onCellClicked(
        state: TicTacToeState,
        rowIndex: Int,
        columnIndex: Int,
        updateState: (TicTacToeState) -> Unit,
        triggerAIMove: () -> Unit
    )
}
