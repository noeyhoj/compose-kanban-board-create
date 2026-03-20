package woowacourse.kanban.board.component.kanbanBoard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KanbanBoardTitleBar(modifier: Modifier = Modifier, progress: Float, doneCount: Int, totalStatusCount: Int, onCreateClick: () -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    "Compose Desktop 칸반 보드",
                    color = Color(0xFF101828),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W500,
                )
                Text(
                    "완료율: ${"%.1f".format(progress * 100)}% ($doneCount/$totalStatusCount)",
                    color = Color(0xFF6A7282),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                )
            }
            TaskCreateButton(onCreateClick = onCreateClick)
        }
        Box(modifier = Modifier.height(16.dp))
        ProjectProgress(progress = progress)
    }
}

@Composable
private fun TaskCreateButton(onCreateClick: () -> Unit) {
    Button(
        colors = ButtonColors(
            containerColor = Color(0xFF4F39F6),
            contentColor = Color(0xFF4F39F6),
            disabledContainerColor = Color(0xFF4F39F6),
            disabledContentColor = Color(0xFF4F39F6),
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = onCreateClick,
    ) {
        Text("+ 새 태스크 생성", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.W400)
    }
}

@Composable
private fun ProjectProgress(progress: Float) {
    LinearProgressIndicator(
        gapSize = 0.dp,
        strokeCap = StrokeCap.Square,
        progress = { progress },
        modifier = Modifier
            .clip(shape = CircleShape)
            .fillMaxWidth()
            .height(8.dp),
        color = Color.Blue,
        trackColor = Color.LightGray,
        drawStopIndicator = {}
    )
}

@Preview(showBackground = true, widthDp = 500)
@Composable
fun KanbanBoardTitleBarPreview() {
    KanbanBoardTitleBar(progress = 0f, doneCount = 0, totalStatusCount = 0, onCreateClick = {})
}
