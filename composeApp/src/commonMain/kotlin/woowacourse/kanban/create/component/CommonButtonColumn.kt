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
fun CommonButtonColumn(
    modifier: Modifier = Modifier,
    header: String,
    items: List<String>,
    composable: @Composable (String, Boolean, () -> Unit) -> Unit,
) {
    var selectedIndex: Int by remember { mutableIntStateOf(0) }

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
                    {
                        selectedIndex = index
                    },

                )
            }
        }
    }
}
