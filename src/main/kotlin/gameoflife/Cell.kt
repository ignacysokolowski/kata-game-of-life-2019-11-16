package gameoflife

class Cell(private val row: Int, private val alive: Boolean = true) {

    fun alive() = Cell(row, alive = true)

    fun dead() = Cell(row, alive = false)

    fun nextGenerationGivenNeighbours(neighbours: Int): Cell {
        return dead()
    }

    fun neighbours() = setOf(leftNeighbour(), rightNeighbour())

    private fun leftNeighbour() = Cell(row - 1)

    private fun rightNeighbour() = Cell(row + 1)

    override fun toString() = "Cell($row, ${if (alive) "alive" else "dead"})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false
        return other.row == row && other.alive == alive
    }

    override fun hashCode() = javaClass.hashCode()
}
