package woowacourse.kanban.board.model

import woowacourse.kanban.board.constant.ERROR_TAG

class Tags(val tags: List<String> = listOf(), val maxTagSize: Int = 5) {
    init {
        require(tags.size <= maxTagSize) { throw IllegalArgumentException("$ERROR_TAG 테그의 개수는 ${maxTagSize}개 이하로 작성해야 합니다.") }
    }
}
