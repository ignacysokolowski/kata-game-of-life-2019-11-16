import gameoflife.Board
import gameoflife.Cell
import gameoflife.dsl.board
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertThat
import org.junit.Test

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
