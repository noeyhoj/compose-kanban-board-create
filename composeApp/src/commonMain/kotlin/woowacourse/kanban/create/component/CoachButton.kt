package woowacourse.kanban.create.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoachButton(
    modifier: Modifier = Modifier,
    name: String,
) {
    Box(modifier = modifier.border(width = 2.dp, color = Color(0xFFE5E7EB), shape = RoundedCornerShape(10.dp))) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "코치 프로필 아이콘", tint = Color(0xFF838383))
            Spacer(modifier = Modifier.width(12.dp))
            Text(name, fontWeight = FontWeight.W500, fontSize = 14.sp, color = Color(0xFF101828))
        }
    }
}
