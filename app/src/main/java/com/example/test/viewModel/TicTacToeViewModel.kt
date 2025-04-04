package com.example.test.viewModel

import android.app.Activity
import android.text.format.DateFormat
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.model.CellState
import com.example.test.model.TicTacToeState
import com.example.test.model.description
import com.example.test.viewModel.manager.AIMoveManager
import com.example.test.viewModel.manager.CheckWinnerImpl
import com.example.test.viewModel.manager.ClickManagerImpl
import com.example.test.viewModel.manager.ICheckWinnerManager
import com.example.test.viewModel.manager.IClickManager
import com.example.test.viewModel.manager.IMoveManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TicTacToeViewModel(
    private val checkManager: ICheckWinnerManager = CheckWinnerImpl,
    private val clickManager: IClickManager = ClickManagerImpl(checkManager),
    private val moveManager: IMoveManager = AIMoveManager
) : ViewModel() {
    private val _state = MutableStateFlow(TicTacToeState())
    val state: StateFlow<TicTacToeState> get() = _state

    private val _historyData: MutableLiveData<MutableList<Pair<String, String>>?> = MutableLiveData()
    val historyData: LiveData<MutableList<Pair<String, String>>?> = _historyData

    private var historyList: List<Pair<String, String>>? = listOf()


    fun setBoard(size: Int) {
        _state.value = _state.value.copy(
            board = List(size) { List(size) { CellState.EMPTY } }
        )
    }

    fun onCellClicked(rowIndex: Int, columnIndex: Int) {
        clickManager.onCellClicked(
            state = _state.value,
            rowIndex = rowIndex,
            columnIndex = columnIndex,
            updateState = { newState -> _state.value = newState },
            triggerAIMove = { aiMakeMove() }
        )
    }

    @org.jetbrains.annotations.VisibleForTesting
    fun aiMakeMove() {
        viewModelScope.launch {
            delay(MOVE_DELAY)
            moveManager.makeMove(_state.value.board) { row, col ->
                onCellClicked(row, col)
            }
        }
    }

    fun resetGame(activity: Activity) {
        _state.value = TicTacToeState()
        activity.recreate()
    }

    fun setHistory() {
        val newHistory = Pair(
            SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date()),
            state.value.winner?.description ?: "Draw"
        )
        historyList = historyList?.plus(newHistory)
        _historyData.value = historyList?.toMutableList()
    }

    companion object {
        private const val MOVE_DELAY: Long = 500
    }
}

