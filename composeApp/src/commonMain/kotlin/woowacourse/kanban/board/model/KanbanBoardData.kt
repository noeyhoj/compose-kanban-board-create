package woowacourse.kanban.board.model

data class KanbanBoardData(val boardList: List<BoardData>) {
    // 객체지향적으로!
    // 물어보자. tall, don't ask

    val totalStatusCount = boardList.size
    val progress = (boardList.count { it.status == Status.DONE }) * 100 / totalStatusCount

    fun getStatusBoard(status: Status): List<BoardData> = boardList.filter { it.status == status }
}
