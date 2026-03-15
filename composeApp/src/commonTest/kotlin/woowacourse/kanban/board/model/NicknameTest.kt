package woowacourse.kanban.board.model

import androidx.compose.ui.test.ExperimentalTestApi
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class NicknameTest {

    @Test
    fun `닉네임이 공백이라면 오류가 발생함`() {
        // given
        val nickname = ""
        // when
        // then
        assertThrows(IllegalArgumentException::class.java) {
            Nickname(nickname)
        }
    }

    @Test
    fun `닉네임에 빈 공간이 있다면 오류가 발생함`() {
        // given
        val nickname = " "
        // when
        // then
        assertThrows(IllegalArgumentException::class.java) {
            Nickname(nickname)
        }
    }

    @Test
    fun `닉네임이 존재한다면 오류가 발생하지 않는다`() {
        // given
        val nickname = "다이노"
        // when
        val nicknameInfo = Nickname(nickname)
        // then
        assertThat(nicknameInfo.nickname).isEqualTo(nickname)
    }
}
