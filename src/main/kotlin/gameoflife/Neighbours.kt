package gameoflife

data class Neighbour(val column: Column, val row: Row)

class Neighbours private constructor(private val column: Column, private val row: Row) {
    companion object {
        fun ofCell(column: Column, row: Row) = Neighbours(column, row)
    }

    fun <T> map(transform: (Neighbour) -> T) = all().map(transform).toSet()

    private fun all() = setOf(
        topLeft(),
        top(),
        topRight(),
        left(),
        right(),
        bottomLeft(),
        bottom(),
        bottomRight()
    )

    private fun topLeft() = Neighbour(column.left(), row.top())
    private fun top() = Neighbour(column, row.top())
    private fun topRight() = Neighbour(column.right(), row.top())
    private fun left() = Neighbour(column.left(), row)
    private fun right() = Neighbour(column.right(), row)
    private fun bottomLeft() = Neighbour(column.left(), row.bottom())
    private fun bottom() = Neighbour(column, row.bottom())
    private fun bottomRight() = Neighbour(column.right(), row.bottom())

}
