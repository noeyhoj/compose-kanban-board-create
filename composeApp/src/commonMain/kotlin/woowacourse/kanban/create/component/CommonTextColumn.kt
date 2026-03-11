package woowacourse.kanban.create.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
) {
    Column(modifier = modifier) {
        HeaderText(title = title)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFF79747E),
                    shape = RoundedCornerShape(4.dp),
                )
                .heightIn(min = height)
                .padding(start = 16.dp, top = 4.dp, bottom = 4.dp),
        ) {

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = placeHolderAlignment) {
                Text(
                    placeHolder,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    color = Color(0xFFAAAAAA),
//                    textAlign = TextAlign.Start,
                )
            }
        }
        if (hintText.isNotBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
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
