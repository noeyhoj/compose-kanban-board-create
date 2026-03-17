package woowacourse.kanban.create.model

enum class Status(val state: String) {
    TODO("To Do"),
    IN_PROGRESS("In Progress"),
    DONE("Done"),
}
