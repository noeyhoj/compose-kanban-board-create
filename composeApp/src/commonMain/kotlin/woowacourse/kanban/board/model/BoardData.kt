package woowacourse.kanban.board.model

data class BoardData(val title: Title, val content: String = "", val tags: Tags, val nickname: Nickname)
