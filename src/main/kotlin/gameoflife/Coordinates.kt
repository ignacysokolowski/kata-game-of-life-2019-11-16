package gameoflife

data class Coordinates(val column: Column, val row: Row) {
    fun movedRight() = Coordinates(column.right(), row)
    fun movedLeft() = Coordinates(column.left(), row)
    fun movedTop() = Coordinates(column, row.top())
    fun movedBottom() = Coordinates(column, row.bottom())
}
