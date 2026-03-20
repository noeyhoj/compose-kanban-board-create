package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import woowacourse.kanban.board.component.kanbanBoard.KanbanBoard
import woowacourse.kanban.board.model.BoardData
import woowacourse.kanban.board.model.KanbanBoardData

@Composable
fun App() {
    val kanbanBoardData = remember { KanbanBoardData(mutableStateListOf()) }
    var showDialog by remember { mutableStateOf(false) }

    fun onCreateClick() {
        showDialog = !showDialog
    }

    fun onDismissRequest() {
        showDialog = false
    }

    fun onTaskAdd(boardData: BoardData) {
        kanbanBoardData.addBoardData(boardData)
    }

    KanbanBoard(
        kanbanBoardData = kanbanBoardData,
        showDialog = showDialog,
        onCreateClick = { onCreateClick() },
        onDismissRequest = { onDismissRequest() },
        onTaskAdd = { onTaskAdd(it) },
    )
}
