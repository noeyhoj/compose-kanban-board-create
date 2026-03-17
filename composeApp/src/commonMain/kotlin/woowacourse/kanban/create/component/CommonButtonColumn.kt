package woowacourse.kanban.create.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CommonButtonColumn(
    header: String,
    items: List<String>,
    selectedIndex: Int,
    onChangeValue: (Int) -> Unit,
    modifier: Modifier = Modifier,
    composable: @Composable (String, Boolean, () -> Unit, Int) -> Unit,
) {
    Column(modifier) {
        HeaderText(title = header)
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth().heightIn(max = 100.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            userScrollEnabled = false,
        ) {
            items(
                items.size,
            ) { index ->
                composable(
                    items[index],
                    selectedIndex == index,
                    { onChangeValue(index) },
                    index,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatusCommonButtonColumnPreview() {
    CommonButtonColumn(
        header = "상태 *",
        items = listOf("To Do", "In Progress", "Done"),
        selectedIndex = 0,
        onChangeValue = {},
    ) { status, isSelected, onClick, index ->
        StatusButton(status = status, isSelected = isSelected, onClick = onClick, index = index)
    }
}

@Preview(showBackground = true)
@Composable
private fun NamesCommonButtonColumnPreview() {
    CommonButtonColumn(
        header = "담당자",
        items = listOf("다이노", "페임스"),
        selectedIndex = 0,
        onChangeValue = {},
    ) { name, isSelected, onClick, index ->
        CoachButton(name = name, isSelected = isSelected, onClick = onClick, index = index)
    }
}
