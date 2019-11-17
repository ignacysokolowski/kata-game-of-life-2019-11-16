package gameoflife

class Cell(private val column: Int, private val row: Int, private val alive: Boolean = true) {

    fun alive() = Cell(column, row, alive = true)

    fun dead() = Cell(column, row, alive = false)

    fun nextGenerationGivenNeighbours(neighbours: Int): Cell {
        if (!alive) return dead()
        return if (neighbours == 2) alive() else dead()
    }

    fun neighbours() = listOf(leftNeighbour(), rightNeighbour())

    private fun leftNeighbour() = Cell(column - 1, row)

    private fun rightNeighbour() = Cell(column + 1, row)

    override fun toString() = "Cell($column, $row, ${if (alive) "alive" else "dead"})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false
        return other.column == column && other.row == row && other.alive == alive
    }

    override fun hashCode() = javaClass.hashCode()
}
