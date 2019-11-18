package gameoflife

abstract class Cell(protected val column: Column, protected val row: Row) {

    companion object {
        fun alive(column: Column, row: Row): Cell = AliveCell(column, row)
        fun dead(column: Column, row: Row): Cell = DeadCell(column, row)
        private fun aliveFrom(neighbour: Neighbour) = alive(neighbour.column, neighbour.row)
    }

    fun alive() = alive(column, row)
    fun dead() = dead(column, row)

    fun nextGenerationGiven(neighboursAlive: Int): Cell {
        return if (willLiveInNextGenerationGiven(neighboursAlive)) alive() else dead()
    }

    protected abstract fun willLiveInNextGenerationGiven(neighboursAlive: Int): Boolean

    fun potentialAliveNeighbours() = Neighbours.ofCell(column, row).map { aliveFrom(it) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false
        if (javaClass != other.javaClass) return false
        return other.column == column && other.row == row
    }

    override fun hashCode() = javaClass.hashCode()
}

private class AliveCell constructor(column: Column, row: Row) : Cell(column, row) {
    override fun willLiveInNextGenerationGiven(neighboursAlive: Int) = neighboursAlive in setOf(2, 3)
    override fun toString() = "Cell.alive($column, $row)"
}

private class DeadCell constructor(column: Column, row: Row) : Cell(column, row) {
    override fun willLiveInNextGenerationGiven(neighboursAlive: Int) = neighboursAlive == 3
    override fun toString() = "Cell.dead($column, $row)"
}
