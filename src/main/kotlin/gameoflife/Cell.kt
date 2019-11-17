package gameoflife

class Cell(private val row: Int, private val alive: Boolean = true) {
    override fun toString(): String {
        return "Cell($row, ${if (alive) "alive" else "dead"})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false
        return other.row == row && other.alive == alive
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    fun alive(): Cell {
        return Cell(row, alive = true)
    }

    fun dead(): Cell {
        return Cell(row, alive = false)
    }

    fun neighbours(): Set<Cell> {
        return setOf(leftNeighbour(), rightNeighbour())
    }

    private fun leftNeighbour(): Cell {
        return Cell(row - 1)
    }

    private fun rightNeighbour(): Cell {
        return Cell(row + 1)
    }
}
