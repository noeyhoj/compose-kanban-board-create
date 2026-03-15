package woowacourse.kanban.create.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CommonTextColumn(
    modifier: Modifier = Modifier,
    title: String,
    placeHolder: String,
    height: Dp,
    placeHolderAlignment: Alignment = Alignment.CenterStart,
    isError: Boolean = false,
    value: String,
    onChangeValue: (String) -> Unit,
) {
    Column(modifier = modifier) {
        HeaderText(title = title)
        Spacer(modifier = Modifier.height(8.dp))
        CommonTextField(
            placeHolder = placeHolder,
            height = height,
            placeHolderAlignment = placeHolderAlignment,
            isError = isError,
            value = value,
            onChangeValue = onChangeValue,
        )
    }
}
