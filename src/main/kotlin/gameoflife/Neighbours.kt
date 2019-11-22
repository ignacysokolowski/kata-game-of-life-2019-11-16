package gameoflife

class Neighbours private constructor(private val coordinates: Coordinates) {

    companion object {
        fun of(coordinates: Coordinates) = Neighbours(coordinates)
    }

    fun <T> map(transform: (Coordinates) -> T) = all().map(transform).toSet()

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

    private fun topLeft() = coordinates.movedLeft().movedUp()
    private fun top() = coordinates.movedUp()
    private fun topRight() = coordinates.movedRight().movedUp()
    private fun left() = coordinates.movedLeft()
    private fun right() = coordinates.movedRight()
    private fun bottomLeft() = coordinates.movedLeft().movedDown()
    private fun bottom() = coordinates.movedDown()
    private fun bottomRight() = coordinates.movedRight().movedDown()

}
