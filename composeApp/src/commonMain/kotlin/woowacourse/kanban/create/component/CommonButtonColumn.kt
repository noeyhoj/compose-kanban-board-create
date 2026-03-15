package woowacourse.kanban.create.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommonButtonColumn(
    modifier: Modifier = Modifier,
    header: String,
    items: List<String>,
    selectedIndex: Int,
    onChangeValue: (Int) -> Unit,
    composable: @Composable (String, Boolean, () -> Unit, Int) -> Unit,
) {
    val selectedIndex = selectedIndex
    Column(modifier) {
        HeaderText(title = header)
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
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
