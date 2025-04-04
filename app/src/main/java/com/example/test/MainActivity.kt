package com.example.test

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.test.ui.compose.TicTacToeGame
import com.example.test.ui.theme.TicTacToeTheme
import com.example.test.viewModel.TicTacToeViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<TicTacToeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                val state by viewModel.state.collectAsState()
                val historyData by viewModel.historyData.observeAsState()
                TicTacToeGame(
                    state = state,
                    historyData = historyData?.toList(),
                    resetGame = {
                        viewModel.setHistory()
                        viewModel.resetGame(this) },
                    onCellClicked = viewModel::onCellClicked,
                    enterSize = {
                        viewModel.setBoard(it.toInt())
                    },
                    history = {
                    }
                )
            }
        }
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)

    }
}