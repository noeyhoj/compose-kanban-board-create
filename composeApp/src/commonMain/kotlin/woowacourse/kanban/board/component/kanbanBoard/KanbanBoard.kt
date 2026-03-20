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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import woowacourse.kanban.board.component.dialog.TaskCreateDialog
import woowacourse.kanban.board.model.BoardData
import woowacourse.kanban.board.model.KanbanBoardData
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.Tag

@Composable
fun KanbanBoard(
    modifier: Modifier = Modifier,
    kanbanBoardData: KanbanBoardData,
    showDialog: Boolean,
    isShowSnackBar: Boolean,
    onCreateClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onTaskAdd: (BoardData) -> Unit,
    showSnackBar: suspend () -> Unit,
    onCancelClick: () -> Unit,
) {
    val statuses = Status.entries

    val names = listOf(
        "다이노",
        "페임스",
    )
    var titleInputValue by rememberSaveable { mutableStateOf("") }
    var contentInputValue by rememberSaveable { mutableStateOf("") }
    var tagsInputValue by rememberSaveable { mutableStateOf("") }

    var isTitleError by rememberSaveable { mutableStateOf(false) }
    var isTagsError by rememberSaveable { mutableStateOf(false) }

    var selectedStatusIndex: Int by rememberSaveable { mutableIntStateOf(0) }
    val isStatusSelected: (index: Int) -> Boolean = { selectedStatusIndex == it }

    var selectedNameIndex: Int by rememberSaveable { mutableIntStateOf(0) }
    val isNamesSelected: (index: Int) -> Boolean = { selectedNameIndex == it }

    val titleOnValueChange = { value: String ->
        titleInputValue = value
        isTitleError = BoardData.isTitleError(titleInputValue)
    }
    val contentOnValueChange = { value: String ->
        contentInputValue = value
    }
    val tagsOnValueChange = { value: String ->
        tagsInputValue = value
        val tags = if (tagsInputValue.isNotBlank()) tagsInputValue.split(",") else emptyList()
        isTagsError = tags.any { Tag.isTagError(it) } || BoardData.isTagsError(tags.map { Tag(it) })
    }
    val statusOnValueChange = { index: Int ->
        selectedStatusIndex = index
    }
    val coachOnValueChange = { index: Int -> selectedNameIndex = index }
    val onCreate = {
        if (titleInputValue.isBlank()) {
            isTitleError = true
        } else {
            val boardData = BoardData(
                title = titleInputValue,
                description = contentInputValue,
                tags = if (tagsInputValue.isNotBlank()) tagsInputValue.split(",").map { Tag(it) } else emptyList(),
                status = statuses[selectedStatusIndex],
                nickname = names[selectedNameIndex],
            )
            onTaskAdd(boardData)
            titleInputValue = ""
            contentInputValue = ""
            tagsInputValue = ""
            selectedStatusIndex = 0
            selectedNameIndex = 0
            onDismissRequest()
        }
    }
    val isCreateError = isTitleError || isTagsError

    Box {
        Column(
            modifier = modifier.background(color = Color.White),
        ) {
            KanbanBoardTitleBar(
                progress = kanbanBoardData.progress(),
                doneCount = kanbanBoardData.doneCount(),
                totalStatusCount = kanbanBoardData.totalStatusCount(),
                onCreateClick = onCreateClick,
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
                    onDismissRequest = onDismissRequest,
                ) {
                    TaskCreateDialog(
                        titleInputValue = titleInputValue,
                        titleOnValueChange = titleOnValueChange,
                        isTitleError = isTitleError,
                        contentInputValue = contentInputValue,
                        contentOnValueChange = contentOnValueChange,
                        tagsInputValue = tagsInputValue,
                        tagsOnValueChange = tagsOnValueChange,
                        isTagsError = isTagsError,
                        statuses = statuses,
                        statusOnValueChange = statusOnValueChange,
                        names = names,
                        coachOnValueChange = coachOnValueChange,
                        onCreate = onCreate,
                        onCancel = onDismissRequest,
                        isCreateError = isCreateError,
                        isStatusSelected = isStatusSelected,
                        isNamesSelected = isNamesSelected,
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
            onClick = onCancelClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun KanbanBoardPreview() {
    val kanbanBoardData = KanbanBoardData(mutableListOf())
    KanbanBoard(
        kanbanBoardData = kanbanBoardData,
        showDialog = false,
        onCreateClick = {},
        onDismissRequest = {},
        onTaskAdd = {},
        isShowSnackBar = false,
        showSnackBar = {},
        onCancelClick = { },
    )
}
