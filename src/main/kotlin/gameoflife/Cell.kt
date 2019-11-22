package gameoflife

abstract class Cell(protected val coordinates: Coordinates) {

    companion object {
        fun alive(column: Column, row: Row): Cell = aliveAt(Coordinates(column, row))
        fun aliveAt(coordinates: Coordinates): Cell = AliveCell(coordinates)
        fun dead(column: Column, row: Row): Cell = deadAt(Coordinates(column, row))
        fun deadAt(coordinates: Coordinates): Cell = DeadCell(coordinates)
    }

    fun alive() = aliveAt(coordinates)
    fun dead() = deadAt(coordinates)

    fun nextGenerationGiven(neighboursAlive: Int) =
        if (willLiveInNextGenerationGiven(neighboursAlive)) alive() else dead()

    protected abstract fun willLiveInNextGenerationGiven(neighboursAlive: Int): Boolean

    fun potentialAliveNeighbours() = Neighbours.of(coordinates).map { aliveAt(it) }

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
    override fun toString() = "Cell.aliveAt($coordinates)"
}

private class DeadCell constructor(coordinates: Coordinates) : Cell(coordinates) {
    override fun willLiveInNextGenerationGiven(neighboursAlive: Int) = neighboursAlive == 3
    override fun toString() = "Cell.deadAt($coordinates)"
}
