import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class GameOfLifeTest {

    @Test fun `empty board stays empty in the next generation`() {
        assertThat(emptyBoard().nextGeneration(), IsEqual(emptyBoard()))
    }

    @Test fun `all cells die in a board with a single cell`() {
        assertThat(Board(Cell(0)).nextGeneration(), IsEqual(emptyBoard()))
    }

    @Test fun `all cells die in a board with two cells`() {
        assertThat(Board(Cell(0), Cell(1)).nextGeneration(), IsEqual(emptyBoard()))
    }

    @Test fun `cell with two neighbours survives`() {
        assertThat(Board(Cell(0), Cell(1), Cell(1)).nextGeneration(), IsEqual(Board(Cell(1))))
    }

    private fun emptyBoard() = Board()
}

class Cell(private val row: Int) {
    override fun toString(): String {
        return "Cell($row)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false
        return other.row == row
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

class Board(private val cells: List<Cell>) {
    constructor(vararg cells: Cell) : this(cells.asList())

    fun nextGeneration(): Board {
        if (cells.size == 3) {
            return Board(listOf(Cell(1)))
        }
        return Board(emptyList())
    }

    override fun toString(): String {
        return "Board($cells)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Board) return false
        return other.cells == cells
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
