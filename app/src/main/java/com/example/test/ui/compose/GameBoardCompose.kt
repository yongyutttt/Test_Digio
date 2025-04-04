package com.example.test.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.test.model.CellState
import com.example.test.ui.theme.Sizes

@Composable
fun GameBoard(board: List<List<CellState>>, onCellClicked: (Int, Int) -> Unit) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.Black)
            .padding(Sizes.CELL_PADDING),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { columnIndex, cell ->
                    GameCell(cell, rowIndex, columnIndex, onCellClicked)
                }
            }
        }
    }
}