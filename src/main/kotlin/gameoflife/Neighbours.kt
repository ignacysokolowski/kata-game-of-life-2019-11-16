package gameoflife

data class Neighbour(val column: Column, val row: Row) {
    constructor(coordinates: Coordinates) : this(coordinates.column, coordinates.row)
}

class Neighbours private constructor(column: Column, row: Row) {

    private val coordinates = Coordinates(column, row)

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

    private fun topLeft() = Neighbour(coordinates.movedLeft().movedTop())
    private fun top() = Neighbour(coordinates.movedTop())
    private fun topRight() = Neighbour(coordinates.movedRight().movedTop())
    private fun left() = Neighbour(coordinates.movedLeft())
    private fun right() = Neighbour(coordinates.movedRight())
    private fun bottomLeft() = Neighbour(coordinates.movedLeft().movedBottom())
    private fun bottom() = Neighbour(coordinates.movedBottom())
    private fun bottomRight() = Neighbour(coordinates.movedRight().movedBottom())

}
