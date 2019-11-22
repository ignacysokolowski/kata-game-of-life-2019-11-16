package gameoflife

data class Coordinates(val column: Column, val row: Row) {
    fun movedRight() = Coordinates(column.right(), row)
    fun movedLeft() = Coordinates(column.left(), row)
    fun movedUp() = Coordinates(column, row.top())
    fun movedDown() = Coordinates(column, row.bottom())
}
