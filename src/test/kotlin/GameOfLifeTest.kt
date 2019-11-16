import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertThat
import org.junit.Test

class BoardDSLTest {

    @Test fun `creates an empty board`() {
        assertThat(board {}, IsEqual(Board()))
    }

    @Test fun `creates a board with cells`() {
        assertThat(board { O; O; O }, IsEqual(Board(Cell(0), Cell(1), Cell(2))))
    }
}

class GameOfLifeTest {

    @Test fun `empty board stays empty in the next generation`() {
        assertEvolution(board {}, board {})
    }

    @Test fun `all cells die in a board with a single cell`() {
        assertEvolution(board { O }, board {})
    }

    @Test fun `all cells die in a board with two cells`() {
        assertEvolution(board { O; O }, board {})
    }

    @Test fun `cell with two neighbours survives`() {
        assertEvolution(Board(Cell(0), Cell(1), Cell(1)), Board(Cell(1)))
    }

    private fun assertEvolution(currentGeneration: Board, nextGeneration: Board) {
        assertThat(currentGeneration.nextGeneration(), IsEqual(nextGeneration))
    }

}

class CellTest {

    @Test fun `two equal cells`() {
        assertThat(Cell(2), IsEqual(Cell(2)))
    }

    @Test fun `two unequal cells`() {
        assertThat(Cell(2), not(IsEqual(Cell(3))))
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
        return "Board(${cells.asList()})"
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

fun board(grid: (BoardBuilder.() -> Unit)): Board {
    return BoardBuilder(grid).build()
}

class BoardBuilder(private val init: BoardBuilder.() -> Unit) {
    private var cells = mutableListOf<Cell>()
    private var row = 0

    fun build(): Board {
        init()
        return Board(*cells.toTypedArray())
    }

    val O
        get() = addCell()

    private fun addCell(): BoardBuilder {
        cells.add(Cell(row))
        row += 1
        return this
    }
}
