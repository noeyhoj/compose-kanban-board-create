package woowacourse.kanban.board.model

import androidx.compose.ui.test.ExperimentalTestApi
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class TitleTest {

    @Test
    fun `제목이 비어있는 경우 오류가 발생함`() {
        // given
        val title = " "
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
}
