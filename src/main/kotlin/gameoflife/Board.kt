package gameoflife

class Board(private val cells: Cells) {

    constructor(vararg cells: Cell) : this(Cells(cells.asList()))

    fun nextGeneration() = Board(cells.map {
        it.nextGenerationGivenNeighbours(Cells(it.potentialAliveNeighbours()).onlyExistingIn(cells).size())
    })

    override fun toString() = "Board(${cells})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Board) return false
        return other.cells == cells
    }

    override fun hashCode() = javaClass.hashCode()

}
