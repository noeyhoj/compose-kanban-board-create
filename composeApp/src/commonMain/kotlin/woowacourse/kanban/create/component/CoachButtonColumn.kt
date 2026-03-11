package woowacourse.kanban.create.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CoachButtonColumn(
    modifier: Modifier = Modifier,
    header: String,
    items: List<String>,
) {
    var selectedIndex: Int by remember { mutableIntStateOf(0) }

    val names = listOf(
        "다이노",
        "페임스",
    )

    Column {
        HeaderText(title = header)
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                items.size,
            ) { index ->
                CoachButton(
                    name = names[index],
                    onClick = {
                        selectedIndex = index
                    },
                    isSelected = selectedIndex == index,
                )
            }
        }
    }
}
