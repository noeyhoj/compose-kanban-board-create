package woowacourse.kanban.board

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import woowacourse.kanban.board.component.kanbanBoard.KanbanBoard
import woowacourse.kanban.board.model.BoardData
import woowacourse.kanban.board.model.KanbanBoardData

@Composable
fun App() {
    val kanbanBoardData = remember { KanbanBoardData(mutableStateListOf()) }
    var showDialog by remember { mutableStateOf(false) }
    var isShowSnackBar by remember { mutableStateOf(false) }

    fun onCreateClick() {
        showDialog = !showDialog
    }

    fun onDismissRequest() {
        showDialog = false
    }

    fun onTaskAdd(boardData: BoardData) {
        kanbanBoardData.addBoardData(boardData)
        isShowSnackBar = true
    }

    suspend fun showSnackBar() {
        delay(3000.milliseconds)
        isShowSnackBar = false
    }

    fun onCancelClick() {
        isShowSnackBar = false
    }

    KanbanBoard(
        modifier = Modifier.background(color = Color.White),
        kanbanBoardData = kanbanBoardData,
        showDialog = showDialog,
        onCreateClick = { onCreateClick() },
        onDismissRequest = { onDismissRequest() },
        onTaskAdd = { onTaskAdd(it) },
        isShowSnackBar = isShowSnackBar,
        showSnackBar = { showSnackBar() },
        onCancelClick = { onCancelClick() },
    )
}
