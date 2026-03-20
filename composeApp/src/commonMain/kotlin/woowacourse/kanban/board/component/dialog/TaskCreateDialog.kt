package woowacourse.kanban.board.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.Status

@Composable
fun TaskCreateDialog(
    modifier: Modifier = Modifier,
    titleInputValue: String,
    titleOnValueChange: (String) -> Unit,
    isTitleError: Boolean,
    contentInputValue: String,
    contentOnValueChange: (String) -> Unit,
    tagsInputValue: String,
    tagsOnValueChange: (String) -> Unit,
    isTagsError: Boolean,
    statuses: List<Status>,
    statusOnValueChange: (Int) -> Unit,
    names: List<String>,
    coachOnValueChange: (Int) -> Unit,
    onCreate: () -> Unit,
    onCancel: () -> Unit,
    isCreateError: Boolean,
    isStatusSelected: (Int) -> Boolean,
    isNamesSelected: (Int) -> Boolean,
) {

    Column(
        modifier = modifier
            .background(color = Color.White)
            .verticalScroll(rememberScrollState()),
    ) {
        DialogBar(
            modifier = Modifier.padding(
                vertical = 28.dp,
                horizontal = 24.dp,
            )
                .fillMaxWidth(),
            onClick = onCancel,
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
                onValueChange = titleOnValueChange,
                isError = isTitleError,
                placeholderText = "태스크 제목을 입력하세요",
            )
            CommonTextColumn(
                modifier = Modifier.fillMaxWidth().height(116.dp),
                title = "설명",
                content = contentInputValue,
                onValueChange = contentOnValueChange,
                placeholderText = "태스크에 대한 자세한 설명을 입력하세요",
            )
            CommonTextColumn(
                modifier = Modifier.fillMaxWidth(),
                title = "태그 *",
                content = tagsInputValue,
                onValueChange = tagsOnValueChange,
                isError = isTagsError,
                placeholderText = "태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)",
                isSupportingText = true,
            )
            CommonButtonColumn(
                header = "상태 *",
                items = statuses.map { it.state },
                isSelected = isStatusSelected,
                onValueChange = statusOnValueChange,
            ) { status, isSelected, onClick ->
                StatusButton(status = status, isSelected = isSelected, onClick = onClick)
            }
            CommonButtonColumn(
                header = "담당자 *",
                items = names,
                isSelected = isNamesSelected,
                onValueChange = coachOnValueChange,
            ) { name, isSelected, onClick ->
                CoachButton(name = name, isSelected = isSelected, onClick = onClick)
            }
            HorizontalDivider()
            FooterRow(
                onCancel = onCancel,
                onCreate = onCreate,
                isCreateError = isCreateError,
            )
        }
    }
}
