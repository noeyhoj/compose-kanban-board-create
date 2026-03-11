package woowacourse.kanban.create.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonTextColumn(
    modifier: Modifier = Modifier,
    title: String,
    placeHolder: String,
    hintText: String = "",
    height: Dp,
    placeHolderAlignment: Alignment = Alignment.CenterStart,
    isError: Boolean = false,
    value: String,
    onChangeValue: (String) -> Unit,
) {
    Column(modifier = modifier) {
        HeaderText(title = title)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = value,
            onValueChange = onChangeValue,
            modifier = Modifier.fillMaxWidth()
                .border(
                    width = if (isError) 2.dp else 1.dp,
                    color = if (isError) Color(0xFFB3261E) else Color(0xFF79747E),
                    shape = RoundedCornerShape(4.dp),
                )
                .heightIn(min = height)
                .padding(start = 16.dp, top = 4.dp, bottom = 4.dp),
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
            ),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = placeHolderAlignment) {
                    if (value.isEmpty()) {
                        if (isError) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(
                                    "이건,,,,올바르지 않은 형식입니다,,,,,,,,,",
                                    fontWeight = FontWeight.W400,
                                    fontSize = 16.sp,
                                    color = Color(0xFFB3261E),
                                )
                                Box(
                                    modifier = Modifier.size(48.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Error,
                                        contentDescription = "오류 아이콘",
                                        tint = Color(0xFFB3261E),
                                        modifier = Modifier.size(20.dp),
                                    )
                                }
                            }
                        } else {
                            Text(
                                placeHolder,
                                fontWeight = FontWeight.W400,
                                fontSize = 16.sp,
                                color = Color(0xFFAAAAAA),
                            )
                        }
                    } else {
                        innerTextField()
                    }
                }
            },
        )

        if (hintText.isNotBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            if (isError) {
                Text(
                    "태그 형식이 올바르지 않습니다.",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontWeight = FontWeight.W400,
                    fontSize = 12.sp,
                    color = Color(0xFFB3261E),
                )
            } else {
                Text(
                    hintText,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontWeight = FontWeight.W400,
                    fontSize = 12.sp,
                    color = Color(0xFF49454F),
                )
            }
        }
    }
}
