package woowacourse.kanban.board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.component.KanbanBoardTemplate
import woowacourse.kanban.board.constant.DEFAULT_CONTENT
import woowacourse.kanban.board.constant.DEFAULT_NAME
import woowacourse.kanban.board.constant.DEFAULT_TITLE
import woowacourse.kanban.board.constant.MAX_CONTENT
import woowacourse.kanban.board.constant.MAX_NAME
import woowacourse.kanban.board.constant.MAX_TITLE
import woowacourse.kanban.board.model.BoardData
import woowacourse.kanban.board.model.Nickname
import woowacourse.kanban.board.model.Tags
import woowacourse.kanban.board.model.Title

@Composable
fun App() {
    val values = listOf(
        BoardData(
            title = Title(DEFAULT_TITLE),
            content = DEFAULT_CONTENT,
            tags = Tags(listOf("컴포넌트", "성능")),
            nickname = Nickname(DEFAULT_NAME),
        ),
        BoardData(
            title = Title(DEFAULT_TITLE),
            tags = Tags(listOf("컴포넌트", "성능")),
            nickname = Nickname(DEFAULT_NAME),
        ),
        BoardData(
            title = Title(DEFAULT_TITLE),
            content = DEFAULT_CONTENT,
            tags = Tags(),
            nickname = Nickname(DEFAULT_NAME),
        ),
        BoardData(
            title = Title(DEFAULT_TITLE),
            tags = Tags(),
            nickname = Nickname(DEFAULT_NAME),
        ),
        BoardData(
            title = Title(MAX_TITLE),
            content = MAX_CONTENT,
            tags = Tags(listOf("너무너무", "긴 태그", "최대로", "5자까지", "5개제한임")),
            nickname = Nickname(MAX_NAME),
        ),
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(values) { item ->
            KanbanBoardTemplate(item)
        }
    }
}
