package woowacourse.kanban.board.component.dialog

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import kotlin.collections.listOf
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

@OptIn(ExperimentalTestApi::class)
class DialogTest {

    @Test
    fun `상태 버튼을 클릭 했을 때 버튼 인덱스가 변경된다`() = runComposeUiTest {
        var selectedStatusIndex = 0

        val statusOnValueChange = { index: Int ->
            selectedStatusIndex = index
        }

        val isStatusSelected: (index: Int) -> Boolean = { selectedStatusIndex == it }

        setContent {
            CommonButtonColumn(
                header = "상태",
                items = listOf("To Do", "In Progress", "Done"),
                isSelected = isStatusSelected,
                onValueChange = statusOnValueChange,
            ) { status, isSelected, onClick ->
                StatusButton(status = status, isSelected = isSelected, onClick = onClick)
            }
        }

        onNodeWithText("In Progress").performClick()
        waitForIdle()
        assertThat(selectedStatusIndex).isEqualTo(1)
    }

    @Test
    fun `담당자 버튼을 클릭 했을 때 버튼 인덱스가 변경된다`() = runComposeUiTest {
        var selectedNameIndex = 0

        val namesOnValueChange = { index: Int ->
            selectedNameIndex = index
        }

        val isNamesSelected: (index: Int) -> Boolean = { selectedNameIndex == it }

        setContent {
            CommonButtonColumn(
                header = "담당자",
                items = listOf("다이노", "페임스"),
                isSelected = isNamesSelected,
                onValueChange = namesOnValueChange,
            ) { name, isSelected, onClick ->
                CoachButton(name = name, isSelected = isSelected, onClick = onClick)
            }
        }

        onNodeWithText("페임스").performClick()
        assertThat(selectedNameIndex).isEqualTo(1)
    }

    @Test
    fun `텍스트 필드에 입력한 내용이 입력한대로 출력되어야 한다`() = runComposeUiTest {
        var titleInputValue = ""
        var isTitleError = false
        val titleOnValueChange = { value: String ->
            titleInputValue = value
            isTitleError = titleInputValue.isBlank()
        }

        setContent {
            CommonTextColumn(
                title = "제목 *",
                content = titleInputValue,
                onValueChange = titleOnValueChange,
                placeholderText = "태스크 제목을 입력하세요",
                isError = isTitleError,
            )
        }

        onNodeWithText("태스크 제목을 입력하세요").performTextInput("제목입니다")
        assertThat(titleInputValue).isEqualTo("제목입니다")
    }

    @Test
    fun `제목 검증 실패시 에러 표시가 출력되야 한다`() = runComposeUiTest {
        val isTitleError = true

        setContent {
            CommonTextColumn(
                title = "제목 *",
                content = "",
                onValueChange = {},
                placeholderText = "태스크 제목을 입력하세요",
                isError = isTitleError,
            )
        }

        onNodeWithText("이건,,,,올바르지 않은 형식입니다,,,,,,,,,").assertExists()
    }

    @Test
    fun `태그 검증 실패시 에러 표시가 출력되야 한다`() = runComposeUiTest {
        val isTagsError = true

        setContent {
            CommonTextColumn(
                title = "태그",
                content = "",
                onValueChange = {},
                placeholderText = "",
                isError = isTagsError,
                isSupportingText = true,
            )
        }

        onNodeWithText("태그 형식이 올바르지 않습니다.").assertExists()
    }

    @Test
    fun `제목 검증 혹은 태그 검증에 실패시 생성 버튼 비활성화 되어야 한다`() = runComposeUiTest {
        val isCreateError = true

        setContent {
            FooterRow(
                onCancel = {},
                onCreate = {},
                isCreateError = isCreateError,
            )
        }

        onNodeWithText("생성").assertIsNotEnabled()
    }

    @Test
    fun `제목 검증과 태그 검증에 성공하면 생성 버튼이 활성화 되어야 한다`() = runComposeUiTest {
        val isCreateError = false
        var count = 0

        setContent {
            FooterRow(
                onCancel = {},
                onCreate = { count += 1 },
                isCreateError = isCreateError,
            )
        }

        onNodeWithText("생성").performClick()
        assertThat(count).isEqualTo(1)
        onNodeWithText("생성").assertIsEnabled()
    }
}
