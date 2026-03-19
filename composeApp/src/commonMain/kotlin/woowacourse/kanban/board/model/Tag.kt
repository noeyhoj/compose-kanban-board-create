package woowacourse.kanban.board.model

data class Tag(val text: String) {
    init {
        val maxTagLength = 5
        require(text.length <= maxTagLength) { "[ERROR] 태그의 글자수는 ${maxTagLength}글자 이하여야 합니다." }
        require(text.all { it.toString().isNotBlank() }) { "[ERROR] 태그에 공백이 존재하면 안됩니다." }
    }
}
