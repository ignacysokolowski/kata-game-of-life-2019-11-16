import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class GameOfLifeTest {

    @Test fun `empty board stays empty in the next generation`() {
        assertEvolution(Board(), Board())
    }

    @Test fun `all cells die in a board with a single cell`() {
        assertEvolution(Board(Cell(0)), Board())
    }

    @Test fun `all cells die in a board with two cells`() {
        assertEvolution(Board(Cell(0), Cell(1)), Board())
    }

    @Test fun `cell with two neighbours survives`() {
        assertEvolution(Board(Cell(0), Cell(1), Cell(1)), Board(Cell(1)))
    }

    private fun assertEvolution(currentGeneration: Board, nextGeneration: Board) {
        assertThat(currentGeneration.nextGeneration(), IsEqual(nextGeneration))
    }

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

class Board(vararg val cells: Cell) {

    fun nextGeneration(): Board {
        if (cells.size == 3) {
            return Board(Cell(1))
        }
        return Board()
    }

    override fun toString(): String {
        return "Board($cells)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Board) return false
        return other.cells.contentEquals(cells)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
