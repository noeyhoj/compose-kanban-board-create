package woowacourse.kanban.create.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.create.constant.TEXT_FIELD_BORDER
import woowacourse.kanban.create.constant.TEXT_FIELD_ERROR
import woowacourse.kanban.create.constant.TEXT_FIELD_PLACEHOLDER

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    placeHolder: String,
    height: Dp,
    placeHolderAlignment: Alignment = Alignment.CenterStart,
    isError: Boolean = false,
    value: String,
    onChangeValue: (String) -> Unit,
) {
    BasicTextField(
        value = value,
        onValueChange = onChangeValue,
        modifier = modifier.fillMaxWidth()
            .border(
                width = if (isError) 2.dp else 1.dp,
                color = if (isError) Color(TEXT_FIELD_ERROR) else Color(TEXT_FIELD_BORDER),
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
                                color = Color(TEXT_FIELD_ERROR),
                            )
                            Box(
                                modifier = Modifier.size(48.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Error,
                                    contentDescription = "오류 아이콘",
                                    tint = Color(TEXT_FIELD_ERROR),
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                        }
                    } else {
                        Text(
                            placeHolder,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            color = Color(TEXT_FIELD_PLACEHOLDER),
                        )
                    }
                } else {
                    innerTextField()
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun TitleCommonTextFieldPreview() {
    CommonTextField(
        placeHolder = "태스크 제목을 입력하세요",
        height = 48.dp,
        value = "",
        onChangeValue = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun ContentCommonTextFieldPreview() {
    CommonTextColumn(
        modifier = Modifier,
        title = "설명",
        placeHolder = "태스크에 대한 자세한 설명을 입력하세요",
        height = 116.dp,
        placeHolderAlignment = Alignment.TopStart,
        value = "",
        onChangeValue = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun TitleErrorCommonTextFieldPreview() {
    CommonTextColumn(
        modifier = Modifier,
        title = "태그",
        placeHolder = "태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)",
        height = 44.dp,
        value = "",
        onChangeValue = {},
        isError = false,
    )
}
