package woowacourse.kanban.create.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.create.constant.TEXT_FIELD_ERROR
import woowacourse.kanban.create.constant.TEXT_FIELD_HINT

@Composable
fun ErrorHintText(isError: Boolean) {
    Spacer(modifier = Modifier.height(4.dp))
    if (isError) {
        Text(
            "태그 형식이 올바르지 않습니다.",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            color = Color(TEXT_FIELD_ERROR),
        )
    } else {
        Text(
            "5자 이내의 태그를 최대 5개까지 등록할 수 있습니다.",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            color = Color(TEXT_FIELD_HINT),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IsErrorHintTextPreview() {
    ErrorHintText(true)
}

@Preview(showBackground = true)
@Composable
private fun IsNotErrorHintTextPreview() {
    ErrorHintText(false)
}
