import gameoflife.Cell
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertThat
import org.junit.Test

class BoardDSLTest {

    @Test fun `creates an empty board`() {
        assertThat(board {}, IsEqual(Board()))
    }

    @Test fun `creates a board with cells`() {
        assertThat(board { O; X; O }, IsEqual(Board(Cell(0).alive(), Cell(1).dead(), Cell(2).alive())))
    }
}

class GameOfLifeTest {

    @Test fun `empty board stays empty in the next generation`() {
        assertEvolution(board {}, board {})
    }

    @Test fun `all cells die in a board with a single cell`() {
        assertEvolution(board { O }, board { X })
    }

    @Test fun `all cells die in a board with two cells`() {
        assertEvolution(board { O; O }, board { X; X })
    }

    @Test fun `cell with two neighbours survives`() {
        assertEvolution(board { O; O; O }, board { X; O; X })
    }

    private fun assertEvolution(currentGeneration: Board, nextGeneration: Board) {
        assertThat(currentGeneration.nextGeneration(), IsEqual(nextGeneration))
    }

}

class CellTest {

    @Test fun `is alive by default`() {
        assertThat(Cell(2), IsEqual(Cell(2).alive()))
    }

    @Test fun `is not dead by default`() {
        assertThat(Cell(2), not(IsEqual(Cell(2).dead())))
    }

    @Test fun `can come to live`() {
        assertThat(Cell(2).dead().alive(), IsEqual(Cell(2).alive()))
    }

    @Test fun `two equal alive cells`() {
        assertThat(Cell(2).alive(), IsEqual(Cell(2).alive()))
    }

    @Test fun `two equal dead cells`() {
        assertThat(Cell(2).dead(), IsEqual(Cell(2).dead()))
    }

    @Test fun `two unequal alive cells`() {
        assertThat(Cell(2).alive(), not(IsEqual(Cell(3).alive())))
    }

    @Test fun `two unequal dead cells`() {
        assertThat(Cell(2).dead(), not(IsEqual(Cell(3).dead())))
    }

    @Test fun `has neighbours on the left and right`() {
        assertThat(Cell(1).neighbours(), IsEqual(setOf(Cell(0), Cell(2))))
        assertThat(Cell(2).neighbours(), IsEqual(setOf(Cell(1), Cell(3))))
    }
}

class Cells(private val cells: List<Cell>) {
    fun size() = cells.size

    fun allDead(): Cells {
        return Cells(cells.map { it.dead() })
    }

    override fun toString(): String {
        return "Cells(${cells})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cells) return false
        return other.cells == cells
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

class Board(private val cells: Cells) {

    constructor(vararg cells: Cell) : this(Cells(cells.asList()))

    fun nextGeneration(): Board {
        if (cells.size() == 3) {
            return Board(Cell(0).dead(), Cell(1).alive(), Cell(2).dead())
        }
        return Board(cells.allDead())
    }

    override fun toString(): String {
        return "Board(${cells})"
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

fun board(grid: (BoardBuilder.() -> Unit)): Board {
    return BoardBuilder(grid).build()
}

class BoardBuilder(private val init: BoardBuilder.() -> Unit) {
    private var cells = mutableListOf<Cell>()
    private var row = 0

    fun build(): Board {
        init()
        return Board(Cells(cells))
    }

    val O
        get() = addCell(nextCell().alive())

    val X
        get() = addCell(nextCell().dead())

    private fun nextCell() = Cell(row)

    private fun addCell(cell: Cell): BoardBuilder {
        cells.add(cell)
        row += 1
        return this
    }
}
