package woowacourse.kanban.board.model

import androidx.compose.ui.test.ExperimentalTestApi
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertThrows

@OptIn(ExperimentalTestApi::class)
class TagsTest {

    @Test
    fun `태그의 개수가 최대 태그 수를 초과면 오류가 발생한다`() {
        // given
        val tags = listOf("일", "이", "삼", "사", "오", "육")
        val maxTagSize = 5
        // when
        // then
        assertThrows(IllegalArgumentException::class.java) {
            Tags(tags = tags, maxTagSize = maxTagSize)
        }
    }

    @Test
    fun `태그의 개수가 최대 태그 수 이하면 오류가 발생하지 않는다`() {
        // given
        val tags = listOf("일", "이", "삼", "사", "오")
        val maxTagSize = 5
        // when
        val tagsData = Tags(tags = tags, maxTagSize = maxTagSize)
        // then
        assertThat(tagsData.tags).isEqualTo(tags)
    }
}
