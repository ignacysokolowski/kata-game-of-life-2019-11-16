package gameoflife

class Universe(private val cells: Cells) {

    fun nextGeneration() =
        Universe(cells.map { it.nextGenerationGiven(aliveNeighboursOf(it)) })

    private fun aliveNeighboursOf(cell: Cell) =
        Cells(cell.potentialAliveNeighbours()).onlyExistingIn(cells).size()

    override fun toString() = "Universe(${cells})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Universe) return false
        return other.cells == cells
    }

    override fun hashCode() = javaClass.hashCode()

}
