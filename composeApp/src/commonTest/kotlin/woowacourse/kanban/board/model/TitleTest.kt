package woowacourse.kanban.board.model

import androidx.compose.ui.test.ExperimentalTestApi
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class TitleTest {

    @Test
    fun `제목이 비어있는 경우 오류가 발생함`() {
        // given
        val title = ""
        // when
        // then
        Assert.assertThrows(IllegalArgumentException::class.java) {
            Title(title)
        }
    }

    @Test
    fun `제목이 공백인 경우 오류가 발생함`() {
        // given
        val title = " "
        // when
        // then
        Assert.assertThrows(IllegalArgumentException::class.java) {
            Title(title)
        }
    }

    @Test
    fun `제목이 존재하는 경우 오류가 발생하지 않는다`() {
        // given
        val title = "이것은 제목입니다"
        // when
        val titleInfo = Title(title)
        // then
        assertThat(titleInfo.content).isEqualTo(title)
    }
}
