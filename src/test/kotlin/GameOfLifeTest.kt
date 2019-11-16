import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class GameOfLifeTest {

    @Test fun `empty board stays empty in the next generation`() {
        assertThat(Board(emptyList()).nextGeneration(), IsEqual(Board(emptyList())))
    }

    @Test fun `all cells die in a board with a single cell`() {
        assertThat(Board(listOf(Cell())).nextGeneration(), IsEqual(Board(emptyList())))
    }

    @Test fun `all cells die in a board with two cells`() {
        assertThat(Board(listOf(Cell(), Cell())).nextGeneration(), IsEqual(Board(emptyList())))
    }

    @Test fun `cell with two neighbours survives`() {
        assertThat(Board(listOf(Cell(), Cell(), Cell())).nextGeneration(), IsEqual(Board(listOf(Cell()))))
    }
}

class Cell {
    override fun toString(): String {
        return "Cell()"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

class Board(private val cells: List<Cell>) {

    fun nextGeneration(): Board {
        if (cells.size == 3) {
            return Board(listOf(Cell()))
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
