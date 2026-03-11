package woowacourse.kanban.create.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(
    showBackground = true,
    widthDp = 672,
    heightDp = 900,
)
fun TaskCreateDialog(modifier: Modifier = Modifier) {
    val statuses = listOf(
        "To Do",
        "In Progress",
        "Done",
    )

    val names = listOf(
        "다이노",
        "페임스",
    )

    Column(modifier = Modifier.background(color = Color.White).size(width = 672.dp, height = 900.dp)) {
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
                title = "제목 *",
                placeHolder = "태스크 제목을 입력하세요",
                height = 48.dp,
            )
            CommonTextColumn(
                modifier = Modifier,
                title = "설명",
                placeHolder = "태스크에 대한 자세한 설명을 입력하세요",
                height = 116.dp,
                placeHolderAlignment = Alignment.TopStart,
            )
            CommonTextColumn(
                modifier = Modifier,
                title = "태그",
                placeHolder = "태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)",
                height = 44.dp,
                hintText = "5자 이내의 태그를 최대 5개까지 등록할 수 있습니다.",
            )
            CommonButtonColumn(
                modifier = Modifier.fillMaxWidth(),
                header = "상태 *",
                items = statuses,
            )
            CoachButtonColumn(
                modifier = Modifier.fillMaxWidth(),
                header = "담당자 *",
                items = names,
            )
            HorizontalDivider()
            FooterRow()
        }
    }
}
