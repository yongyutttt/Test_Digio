package com.example.test.ui.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.model.CellState
import com.example.test.model.description
import com.example.test.ui.theme.Sizes
import com.example.test.ui.theme.TicTacToeTheme

private const val CELLS_PER_ROW = 3

@Composable
fun GameCell(
    cell: CellState,
    rowIndex: Int,
    columnIndex: Int,
    onCellClicked: (Int, Int) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val cellSize = minOf(screenWidth, screenHeight) / CELLS_PER_ROW
    val fontSize = cellSize / CELLS_PER_ROW

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .testTag("Cell: $rowIndex,$columnIndex")
            .size(Sizes.CELL_SIZE)
            .padding(Sizes.CELL_PADDING)
            .background(Color.White)
            .clickable { onCellClicked(rowIndex, columnIndex) }
    ) {
        AnimatedVisibility(
            visible = cell != CellState.EMPTY,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Text(
                text = cell.description,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = fontSize.value.sp),
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
private fun GameOverCellPreviewX() {
    TicTacToeTheme {
        GameCell(cell = CellState.X, 0, 0) { _, _ -> }
    }
}

@Preview
@Composable
private fun GameOverCellPreviewO() {
    TicTacToeTheme {
        GameCell(cell = CellState.O, 0, 0) { _, _ -> }
    }
}

@Preview
@Composable
private fun GameOverCellPreviewEmpty() {
    TicTacToeTheme {
        GameCell(cell = CellState.EMPTY, 0, 0) { _, _ -> }
    }
}