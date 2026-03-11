package woowacourse.kanban.create.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusButton(
    modifier: Modifier = Modifier,
    status: String,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    Box(
        modifier =
        if (!isSelected) {
            modifier.border(width = 2.dp, color = Color(0xFFE5E7EB), shape = RoundedCornerShape(10.dp))
        } else {
            modifier.border(width = 2.dp, color = Color(0xFF1447E6), shape = RoundedCornerShape(10.dp))
                .background(Color(0xFFEFF6FF))
        }.clickable(
            onClick = onClick,
        ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            status,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 14.dp),
            color = if (!isSelected) {
                Color(0xFF101828)
            } else {
                Color(0xFF1447E6)
            },

        )
    }
}
