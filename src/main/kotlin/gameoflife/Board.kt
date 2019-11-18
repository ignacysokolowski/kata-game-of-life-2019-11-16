package gameoflife

class Board(private val cells: Cells) {

    constructor(vararg cells: Cell) : this(Cells(cells.toSet()))

    fun nextGeneration() =
        Board(cells.map { it.nextGenerationGiven(aliveNeighboursOf(it)) })

    private fun aliveNeighboursOf(cell: Cell) =
        Cells(cell.potentialAliveNeighbours()).onlyExistingIn(cells).size()

    override fun toString() = "Board(${cells})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Board) return false
        return other.cells == cells
    }

    override fun hashCode() = javaClass.hashCode()

}
