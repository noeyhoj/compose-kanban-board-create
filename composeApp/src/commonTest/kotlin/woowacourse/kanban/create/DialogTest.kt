package woowacourse.kanban.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import woowacourse.kanban.create.component.CoachButton
import woowacourse.kanban.create.component.CommonButtonColumn
import woowacourse.kanban.create.component.StatusButton
import woowacourse.kanban.create.component.TaskCreateDialog

@OptIn(ExperimentalTestApi::class)
class DialogTest {

    @Test
    fun `상태 버튼을 클릭 했을 때 다른 상태 버튼은 선택되지 않아야 한다`() = runComposeUiTest {

        // given
        val statuses = listOf(
            "To Do",
            "In Progress",
            "Done",
        )

        setContent {
            var selectedStatusIndex by remember { mutableIntStateOf(0) }

            CommonButtonColumn(
                header = "상태 *",
                items = statuses,
                selectedIndex = selectedStatusIndex,
                onChangeValue = { index: Int ->
                    selectedStatusIndex = index
                },
            ) { status, isSelected, onClick, index ->
                StatusButton(status = status, isSelected = isSelected, onClick = onClick, index = index)
            }
        }

        // when
        onNodeWithText("In Progress").performClick()
        waitForIdle()
        // then
        onNodeWithTag("selected1").assertExists()
        onNodeWithTag("unselected0").assertExists()
        onNodeWithTag("unselected2").assertExists()
    }

    @Test
    fun `담당자 버튼을 클릭 했을 때 다른 상태 버튼은 선택되지 않아야 한다`() = runComposeUiTest {
        // given
        val names = listOf(
            "다이노",
            "페임스",
        )

        setContent {
            var selectedNamesIndex by remember { mutableIntStateOf(0) }

            CommonButtonColumn(
                header = "담당자",
                selectedIndex = selectedNamesIndex,
                onChangeValue = { index: Int ->
                    selectedNamesIndex = index
                },
                items = names,
            ) { name, isSelected, onClick, index ->
                CoachButton(name = name, isSelected = isSelected, onClick = onClick, index = index)
            }
        }

        onNodeWithTag("selected0").assertExists()
        onNodeWithTag("unselected1").assertExists()
        // when
        onNodeWithText("페임스").performClick()
        waitForIdle()
        // then
        onNodeWithTag("selected1").assertExists()
        onNodeWithTag("unselected0").assertExists()
    }

    @Test
    fun `제목 검증 혹은 태그 검증에 실패시 생성 버튼 비활성화 되어야 한다`() = runComposeUiTest {
        // given
        setContent {
            TaskCreateDialog()
        }
        // when
        onNodeWithText("태스크 제목을 입력하세요").performTextInput("제목")
        waitForIdle()
        onNodeWithText("태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)").performTextInput("태그6글자이상, 태그")
        waitForIdle()
        onNodeWithText("생성").performClick()
        waitForIdle()
        // then
        onNodeWithText("생성").assertIsNotEnabled()
    }

    @Test
    fun `텍스트 필드에 입력한 내용이 입력한대로 출력되어야 한다`() = runComposeUiTest {
        // given
        setContent {
            TaskCreateDialog()
        }

        // when
        // then
        onNodeWithText("태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)").performTextInput("태그입력")
        onNodeWithText("태스크 제목을 입력하세요").performTextInput("제목입력")
        waitForIdle()
        onNodeWithText("태그입력").assertExists()
        onNodeWithText("제목입력").assertExists()
    }

    @Test
    fun `제목 검증 혹은 태그 검증에 실패시 생성 버튼을 누르면 제목과 태그에서 에러 표시가 출력되야 한다`() = runComposeUiTest {
        // given
        setContent {
            TaskCreateDialog()
        }

        // when
        onNodeWithText("태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)").performTextInput("태그6글자이상")
        waitForIdle()
        onNodeWithText("태스크 제목을 입력하세요").performTextInput("제목")
        waitForIdle()
        // then
        onNodeWithText("태그 형식이 올바르지 않습니다.").assertExists()
    }
}
