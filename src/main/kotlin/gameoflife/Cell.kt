package gameoflife

abstract class Cell(protected val column: Int, protected val row: Int) {

    companion object {
        fun alive(column: Int, row: Int): Cell = AliveCell(column, row)
        fun dead(column: Int, row: Int): Cell = DeadCell(column, row)
    }

    fun alive() = alive(column, row)
    fun dead() = dead(column, row)

    abstract fun nextGenerationGiven(neighboursAlive: Int): Cell

    fun potentialAliveNeighbours() = listOf(
        topLeftNeighbour(),
        topNeighbour(),
        topRightNeighbour(),
        rightNeighbour(),
        bottomRightNeighbour(),
        bottomNeighbour(),
        bottomLeftNeighbour(),
        leftNeighbour()
    )

    private fun topLeftNeighbour() = alive(column - 1, row - 1)
    private fun topNeighbour() = alive(column, row - 1)
    private fun topRightNeighbour() = alive(column + 1, row - 1)
    private fun rightNeighbour() = alive(column + 1, row)
    private fun bottomRightNeighbour() = alive(column + 1, row + 1)
    private fun bottomNeighbour() = alive(column, row + 1)
    private fun bottomLeftNeighbour() = alive(column - 1, row + 1)
    private fun leftNeighbour() = alive(column - 1, row)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false
        if (javaClass != other.javaClass) return false
        return other.column == column && other.row == row
    }

    override fun hashCode() = javaClass.hashCode()
}

private class AliveCell constructor(column: Int, row: Int) : Cell(column, row) {
    override fun nextGenerationGiven(neighboursAlive: Int): Cell {
        return if (neighboursAlive == 2 || neighboursAlive == 3) alive() else dead()
    }
    override fun toString() = "Cell.alive($column, $row)"
}

private class DeadCell constructor(column: Int, row: Int) : Cell(column, row) {
    override fun nextGenerationGiven(neighboursAlive: Int): Cell {
        return if (neighboursAlive == 3) alive() else dead()
    }
    override fun toString() = "Cell.dead($column, $row)"
}
