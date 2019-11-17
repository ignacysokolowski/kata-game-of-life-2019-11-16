package gameoflife

class Cell(private val column: Int, private val alive: Boolean = true) {

    fun alive() = Cell(column, alive = true)

    fun dead() = Cell(column, alive = false)

    fun nextGenerationGivenNeighbours(neighbours: Int): Cell {
        return if (neighbours == 2) alive() else dead()
    }

    fun neighbours() = listOf(leftNeighbour(), rightNeighbour())

    private fun leftNeighbour() = Cell(column - 1)

    private fun rightNeighbour() = Cell(column + 1)

    override fun toString() = "Cell($column, ${if (alive) "alive" else "dead"})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false
        return other.column == column && other.alive == alive
    }

    override fun hashCode() = javaClass.hashCode()
}
