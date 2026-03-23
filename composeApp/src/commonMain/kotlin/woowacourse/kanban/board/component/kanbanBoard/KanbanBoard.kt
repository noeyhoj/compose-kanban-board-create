package woowacourse.kanban.board.component.kanbanBoard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import woowacourse.kanban.board.component.dialog.TaskCreateDialog
import woowacourse.kanban.board.model.BoardData
import woowacourse.kanban.board.model.BoardDataState
import woowacourse.kanban.board.model.KanbanBoardData
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.Tag

@Composable
fun KanbanBoard(modifier: Modifier = Modifier) {
    val kanbanBoardData = remember { KanbanBoardData() }

    var showDialog by remember { mutableStateOf(false) }
    var isShowSnackBar by remember { mutableStateOf(false) }

    fun onCreateClick() {
        showDialog = true
    }

    fun onDismissRequest() {
        showDialog = false
    }

    fun onShowSnackBar() {
        isShowSnackBar = true
    }

    fun onTaskCreate(boardDataState: BoardDataState) {
        if (BoardData.isTitleError(boardDataState.titleInputValue)) {
            boardDataState.isTitleError = true
        } else {
            val boardData = BoardData(
                title = boardDataState.titleInputValue,
                description = boardDataState.descriptionInputValue,
                tags = if (boardDataState.tagsInputValue.isNotBlank()) {
                    boardDataState.tagsInputValue.split(",").map { Tag(it) }
                } else emptyList(),
                status = boardDataState.statusValue,
                nickname = boardDataState.nameValue,
            )
            kanbanBoardData.addBoardData(boardData)
        }
    }

    suspend fun showSnackBar() {
        delay(3000.milliseconds)
        isShowSnackBar = false
    }

    fun onSnackBarCancelClick() {
        isShowSnackBar = false
    }

    Box {
        Column(
            modifier = modifier.background(color = Color.White),
        ) {
            KanbanBoardTitleBar(
                progress = kanbanBoardData.progress(),
                doneCount = kanbanBoardData.doneCount(),
                totalStatusCount = kanbanBoardData.totalStatusCount(),
                onCreateClick = { onCreateClick() },
            )
            Row(
                modifier = Modifier.padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Status.entries.forEach { state ->
                    StatusCardManageBox(boardList = kanbanBoardData.getStatusBoard(state), status = state)
                }
            }

            if (showDialog) {
                Dialog(
                    onDismissRequest = { onDismissRequest() },
                ) {
                    TaskCreateDialog(
                        onTaskCreate = {
                            onTaskCreate(it)
                            onDismissRequest()
                            onShowSnackBar()
                        },
                        onDismissRequest = { onDismissRequest() },
                    )
                }
            }
        }
        LaunchedEffect(isShowSnackBar) {
            showSnackBar()
        }
        if (isShowSnackBar) CreateAlertSnackBar(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(4.dp))
                .background(color = Color(0xFF322F35))
                .padding(start = 16.dp)
                .size(width = 344.dp, height = 48.dp)
                .align(alignment = Alignment.BottomCenter),
            text = "새로운 태스크가 추가되었습니다.",
            onClick = { onSnackBarCancelClick() },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun KanbanBoardPreview() {
    KanbanBoard()
}
