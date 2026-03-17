package woowacourse.kanban.create.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.BoardData
import woowacourse.kanban.board.model.Nickname
import woowacourse.kanban.board.model.Tags
import woowacourse.kanban.board.model.Title
import woowacourse.kanban.create.model.Status

val statuses = Status.entries

val names = listOf(
    Nickname("다이노"),
    Nickname("페임스"),
)

@Composable
fun TaskCreateDialog(modifier: Modifier = Modifier) {
    var titleInputValue by rememberSaveable { mutableStateOf("") }
    var contentInputValue by rememberSaveable { mutableStateOf("") }
    var tagsInputValue by rememberSaveable { mutableStateOf("") }

    var isTitleError by rememberSaveable { mutableStateOf(false) }
    var isTagsError by rememberSaveable { mutableStateOf(false) }

    var selectedStatusIndex: Int by rememberSaveable { mutableIntStateOf(0) }
    var selectedNamesIndex: Int by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .background(color = Color.White)
            .size(width = 672.dp, height = 900.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        DialogBar(
            modifier = Modifier.padding(
                vertical = 28.dp,
                horizontal = 24.dp,
            )
                .fillMaxWidth(),
        )
        HorizontalDivider()
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            CommonTextColumn(
                modifier = Modifier.fillMaxWidth(),
                title = "제목 *",
                content = titleInputValue,
                onValueChange = { value: String ->
                    titleInputValue = value
                    isTitleError = titleInputValue.isBlank()
                },
                isError = isTitleError,
                placeholderText = "태스크 제목을 입력하세요",
            )
            CommonTextColumn(
                modifier = Modifier.fillMaxWidth().height(116.dp),
                title = "설명",
                content = contentInputValue,
                onValueChange = { value: String ->
                    contentInputValue = value
                },
                placeholderText = "태스크에 대한 자세한 설명을 입력하세요",
            )
            CommonTextColumn(
                modifier = Modifier.fillMaxWidth(),
                title = "태그 *",
                content = tagsInputValue,
                onValueChange = { value: String ->
                    tagsInputValue = value
                    val tags = if (tagsInputValue.isNotEmpty()) tagsInputValue.split(",") else emptyList()
                    isTagsError =
                        tags.any { it.length > 5 || it.isBlank() || it.split("").count { value -> value == " " } > 0 } ||
                        tags.size > 5
                },
                isError = isTagsError,
                placeholderText = "태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)",
                isSupportingText = true,
            )
            CommonButtonColumn(
                header = "상태 *",
                items = statuses.map { it.state },
                selectedIndex = selectedStatusIndex,
                onChangeValue = { index: Int ->
                    selectedStatusIndex = index
                },
            ) { status, isSelected, onClick, index ->
                StatusButton(status = status, isSelected = isSelected, onClick = onClick, index = index)
            }
            CommonButtonColumn(
                header = "담당자 *",
                items = names.map { it.nickname },
                selectedIndex = selectedNamesIndex,
                onChangeValue = { index: Int -> selectedNamesIndex = index },
            ) { name, isSelected, onClick, index ->
                CoachButton(name = name, isSelected = isSelected, onClick = onClick, index = index)
            }
            HorizontalDivider()
            FooterRow(
                onCancel = { },
                onCreate = {
                    if (titleInputValue.isBlank()) {
                        isTitleError = true
                    } else {
                        val boardData = BoardData(
                            title = Title(titleInputValue),
                            content = contentInputValue,
                            tags = Tags(tagsInputValue.split(",")),
                            nickname = names[selectedNamesIndex],
                        )
                        println(boardData)
                    }
                },
                isCreateError = isTitleError || isTagsError,
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 672,
    heightDp = 900,
)
@Composable
private fun TaskCreateDialogPreview() {
    TaskCreateDialog()
}
