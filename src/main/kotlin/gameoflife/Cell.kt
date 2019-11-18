package gameoflife

class Cell private constructor(
    private val column: Int,
    private val row: Int,
    private val alive: Boolean
) {

    companion object {
        fun alive(column: Int, row: Int) = Cell(column, row, alive = true)
        fun dead(column: Int, row: Int) = Cell(column, row, alive = false)
    }

    fun alive() = Cell(column, row, alive = true)

    fun dead() = Cell(column, row, alive = false)

    fun nextGenerationGivenNeighbours(neighbours: Int): Cell {
        if (!alive) return dead()
        return if (neighbours == 2) alive() else dead()
    }

    fun potentialAliveNeighbours() = listOf(
        leftNeighbour(),
        rightNeighbour(),
        topNeighbour(),
        bottomNeighbour()
    )

    private fun leftNeighbour() = alive(column - 1, row)

    private fun rightNeighbour() = alive(column + 1, row)

    private fun topNeighbour() = alive(column, row - 1)

    private fun bottomNeighbour() = alive(column, row + 1)

    override fun toString() = "Cell.${if (alive) "alive" else "dead"}($column, $row)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false
        return other.column == column && other.row == row && other.alive == alive
    }

    override fun hashCode() = javaClass.hashCode()
}
