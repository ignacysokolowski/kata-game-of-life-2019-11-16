package gameoflife

data class Neighbour(val column: Int, val row: Int)

class Neighbours private constructor(private val column: Int, private val row: Int) {
    companion object {
        fun ofCell(column: Int, row: Int) = Neighbours(column, row)
    }

    fun <T> map(transform: (Neighbour) -> T) = all().map(transform).toSet()

    private fun all() = setOf(
        topLeft(),
        top(),
        topRight(),
        right(),
        bottomRight(),
        bottom(),
        bottomLeft(),
        left()
    )

    private fun topLeft() = Neighbour(previousColumn(), previousRow())
    private fun top() = Neighbour(column, previousRow())
    private fun topRight() = Neighbour(nextColumn(), previousRow())
    private fun right() = Neighbour(nextColumn(), row)
    private fun bottomRight() = Neighbour(nextColumn(), nextRow())
    private fun bottom() = Neighbour(column, nextRow())
    private fun bottomLeft() = Neighbour(previousColumn(), nextRow())
    private fun left() = Neighbour(previousColumn(), row)

    private fun previousColumn() = column - 1
    private fun nextColumn() = column + 1
    private fun previousRow() = row - 1
    private fun nextRow() = row + 1
}
