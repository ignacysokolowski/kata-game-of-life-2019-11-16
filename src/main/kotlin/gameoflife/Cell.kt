package gameoflife

abstract class Cell(protected val column: Int, protected val row: Int) {

    companion object {
        fun alive(column: Int, row: Int): Cell = AliveCell(column, row)
        fun dead(column: Int, row: Int): Cell = DeadCell(column, row)
    }

    fun alive() = alive(column, row)
    fun dead() = dead(column, row)

    abstract fun nextGenerationGiven(neighbours: Int): Cell

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false
        if (javaClass != other.javaClass) return false
        return other.column == column && other.row == row
    }

    override fun hashCode() = javaClass.hashCode()
}

private class AliveCell constructor(column: Int, row: Int) : Cell(column, row) {
    override fun nextGenerationGiven(neighbours: Int): Cell {
        return if (neighbours == 2) alive() else dead()
    }
    override fun toString() = "Cell.alive($column, $row)"
}

private class DeadCell constructor(column: Int, row: Int) : Cell(column, row) {
    override fun nextGenerationGiven(neighbours: Int) = dead()
    override fun toString() = "Cell.dead($column, $row)"
}
