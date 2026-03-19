package woowacourse.kanban.board.model

data class BoardData(
    val title: String,
    val description: String = "",
    val tags: List<Tag> = emptyList(),
    val status: Status,
    val nickname: String,
) {
    init {
        val maxTagsSize = 5
        require(title.isNotBlank()) { "[ERROR] 제목이 비어있으면 안됩니다." }
        require(tags.size <= maxTagsSize) { "[ERROR] 태그의 개수는 ${maxTagsSize}개 이하로 작성해야 합니다." }
    }
}
