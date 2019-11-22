package gameoflife

data class Neighbour(val coordinates: Coordinates)

class Neighbours private constructor(private val coordinates: Coordinates) {

    companion object {
        fun ofCellAt(coordinates: Coordinates) = Neighbours(coordinates)
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

    private fun topLeft() = Neighbour(coordinates.movedLeft().movedUp())
    private fun top() = Neighbour(coordinates.movedUp())
    private fun topRight() = Neighbour(coordinates.movedRight().movedUp())
    private fun left() = Neighbour(coordinates.movedLeft())
    private fun right() = Neighbour(coordinates.movedRight())
    private fun bottomLeft() = Neighbour(coordinates.movedLeft().movedDown())
    private fun bottom() = Neighbour(coordinates.movedDown())
    private fun bottomRight() = Neighbour(coordinates.movedRight().movedDown())

}
