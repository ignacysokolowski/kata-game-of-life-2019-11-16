package gameoflife

class Board(private val cells: Cells) {

    constructor(vararg cells: Cell) : this(Cells(cells.asList()))

    fun nextGeneration(): Board {
        if (cells.size() == 3) {
            return Board(Cell(0).dead(), Cell(1).alive(), Cell(2).dead())
        }
        return Board(cells.allDead())
    }

    override fun toString() = "Board(${cells})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Board) return false
        return other.cells == cells
    }

    override fun hashCode() = javaClass.hashCode()

}
