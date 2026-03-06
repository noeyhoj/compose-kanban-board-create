package woowacourse.kanban.board.model

import androidx.compose.ui.test.ExperimentalTestApi
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class NicknameTest {

    @Test
    fun `닉네임이 공백이라면 오류가 발생함`() {
        // given
        val nickname = ""
        // when
        // then
        Assert.assertThrows(IllegalArgumentException::class.java) {
            Nickname(nickname)
        }
    }

    @Test
    fun `닉네임에 빈 공간이 있다면 오류가 발생함`() {
        // given
        val nickname = " "
        // when
        // then
        Assert.assertThrows(IllegalArgumentException::class.java) {
            Nickname(nickname)
        }
    }
}
