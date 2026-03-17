package woowacourse.kanban.board.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tag(content: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
    ) {
        Text(content, modifier = Modifier.padding(6.dp), fontSize = 10.sp)
    }
}
