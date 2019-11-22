package gameoflife

abstract class Cell private constructor(column: Column, row: Row) {

    protected constructor(coordinates: Coordinates) : this(coordinates.column, coordinates.row)

    protected val coordinates = Coordinates(column, row)

    companion object {
        fun alive(column: Column, row: Row): Cell = alive(Coordinates(column, row))
        private fun alive(coordinates: Coordinates): Cell = AliveCell(coordinates)
        fun dead(column: Column, row: Row): Cell = dead(Coordinates(column, row))
        private fun dead(coordinates: Coordinates): Cell = DeadCell(coordinates)
        private fun aliveFrom(neighbour: Neighbour) = alive(neighbour.column, neighbour.row)
    }

    fun alive() = alive(coordinates)
    fun dead() = dead(coordinates)

    fun nextGenerationGiven(neighboursAlive: Int): Cell {
        return if (willLiveInNextGenerationGiven(neighboursAlive)) alive() else dead()
    }

    protected abstract fun willLiveInNextGenerationGiven(neighboursAlive: Int): Boolean

    fun potentialAliveNeighbours() = Neighbours.ofCellAt(coordinates).map { aliveFrom(it) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false
        if (javaClass != other.javaClass) return false
        return other.coordinates == this.coordinates
    }

    override fun hashCode() = javaClass.hashCode()
}

private class AliveCell constructor(coordinates: Coordinates) : Cell(coordinates) {
    override fun willLiveInNextGenerationGiven(neighboursAlive: Int) = neighboursAlive in setOf(2, 3)
    override fun toString() = "Cell.alive($coordinates.column, $coordinates.row)"
}

private class DeadCell constructor(coordinates: Coordinates) : Cell(coordinates) {
    override fun willLiveInNextGenerationGiven(neighboursAlive: Int) = neighboursAlive == 3
    override fun toString() = "Cell.dead($coordinates.column, $coordinates.row)"
}
