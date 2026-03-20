package woowacourse.kanban.board.component.kanbanBoard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import woowacourse.kanban.board.component.dialog.FooterRow
import woowacourse.kanban.board.constant.DEFAULT_CONTENT
import woowacourse.kanban.board.constant.DEFAULT_NAME
import woowacourse.kanban.board.constant.DEFAULT_TITLE
import woowacourse.kanban.board.constant.MAX_CONTENT
import woowacourse.kanban.board.constant.MAX_NAME
import woowacourse.kanban.board.constant.MAX_TITLE
import woowacourse.kanban.board.model.BoardData
import woowacourse.kanban.board.model.KanbanBoardData
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.Tag

@OptIn(ExperimentalTestApi::class)
class KanbanBoardTest {

    private val kanbanBoardData = KanbanBoardData(
        mutableListOf(
            BoardData(
                title = DEFAULT_TITLE,
                description = DEFAULT_CONTENT,
                tags = listOf(Tag("컴포넌트"), Tag("성능")),
                status = Status.TODO,
                nickname = DEFAULT_NAME,
            ),
            BoardData(
                title = DEFAULT_TITLE,
                tags = listOf(Tag("컴포넌트"), Tag("성능")),
                status = Status.TODO,
                nickname = DEFAULT_NAME,
            ),
            BoardData(
                title = DEFAULT_TITLE,
                description = DEFAULT_CONTENT,
                status = Status.IN_PROGRESS,
                nickname = DEFAULT_NAME,
            ),
            BoardData(
                title = DEFAULT_TITLE,
                status = Status.TODO,
                nickname = DEFAULT_NAME,
            ),
            BoardData(
                title = MAX_TITLE,
                description = MAX_CONTENT,
                tags = listOf(Tag("너무너무"), Tag("긴태그"), Tag("최대로"), Tag("5자까지"), Tag("5개제한임")),
                status = Status.DONE,
                nickname = MAX_NAME,
            ),
        ),
    )

    @Test
    fun `새 태스크 생성 버튼을 누르면 다이얼로그가 열린다`() = runComposeUiTest {
        var showDialog = false

        setContent {
            fun onCreateClick() {
                showDialog = !showDialog
            }

            KanbanBoardTitleBar(
                progress = 0f,
                doneCount = 0,
                totalStatusCount = 0,
                onCreateClick = { onCreateClick() },
            )
        }

        onNodeWithText("+ 새 태스크 생성").performClick()
        waitForIdle()
        assertThat(showDialog).isEqualTo(true)
    }

    @Test
    fun `현재 상태에 맞게 진행률이 진행바에 나타난다`() = runComposeUiTest {

        setContent {
            KanbanBoardTitleBar(
                progress = kanbanBoardData.progress(),
                doneCount = kanbanBoardData.doneCount(),
                totalStatusCount = kanbanBoardData.totalStatusCount(),
                onCreateClick = {},
            )
        }

        onNodeWithText("완료율: 20.0% (1/5)").assertExists()
    }

    @Test
    fun `존재하는 카드 목록에 맞게 박스가 카드를 그린다`() = runComposeUiTest {

        setContent {
            StatusCardManageBox(kanbanBoardData.boardList, status = Status.TODO)
        }

        onNodeWithText("3").assertExists()
    }

    @Test
    fun `카드가 생성됐을 경우 스낵바가 나타난다`() = runComposeUiTest {
        var isShowSnackBar = false
        val boardData = BoardData(
            title = DEFAULT_TITLE,
            tags = listOf(Tag("컴포넌트")),
            status = Status.TODO,
            nickname = DEFAULT_NAME,
        )

        fun onTaskAdd(boardData: BoardData) {
            isShowSnackBar = true
        }

        setContent {
            FooterRow(
                onCancel = {},
                onCreate = {
                    onTaskAdd(boardData)
                },
                isCreateError = false,
            )
        }
        onNodeWithText("생성").performClick()
        waitForIdle()
        assertThat(isShowSnackBar).isEqualTo(true)
    }

    @Test
    fun `스낵바의 닫기 버튼을 눌렀을 경우 스낵바가 사라진다`() = runComposeUiTest {
        var isShowSnackBar = true

        fun onCancelClick() {
            isShowSnackBar = false
        }

        setContent {
            CreateAlertSnackBar(
                text = "새로운 태스크가 추가되었습니다.",
                onClick = { onCancelClick() },
            )
        }

        onNodeWithContentDescription("닫기 버튼").performClick()
        assertThat(isShowSnackBar).isEqualTo(false)
    }
}
