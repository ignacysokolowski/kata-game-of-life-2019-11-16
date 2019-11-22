package gameoflife

data class Coordinates(private val column: Column, private val row: Row) {
    fun movedRight() = Coordinates(column.right(), row)
    fun movedLeft() = Coordinates(column.left(), row)
    fun movedTop() = Coordinates(column, row.top())
    fun movedBottom() = Coordinates(column, row.bottom())
}
