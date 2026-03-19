package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import woowacourse.kanban.board.component.dialog.TaskCreateDialog
import woowacourse.kanban.board.model.BoardData
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.Tag

@Composable
fun App() {
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
        isTitleError = titleInputValue.isBlank()
    }
    val contentOnValueChange = { value: String ->
        contentInputValue = value
    }
    val tagsOnValueChange = { value: String ->
        tagsInputValue = value
        val tags = if (tagsInputValue.isNotEmpty()) tagsInputValue.split(",") else emptyList()
        isTagsError =
            tags.any { it.length > 5 || it.isBlank() || it.split("").count { value -> value == " " } > 0 } ||
            tags.size > 5
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
                tags = tagsInputValue.split(",").map { Tag(it) },
                status = statuses[selectedStatusIndex],
                nickname = names[selectedNameIndex],
            )
        }
    }
    val isCreateError = isTitleError || isTagsError

    TaskCreateDialog(
        modifier = Modifier,
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
        isCreateError = isCreateError,
        isStatusSelected = isStatusSelected,
        isNamesSelected = isNamesSelected,
    )
}
