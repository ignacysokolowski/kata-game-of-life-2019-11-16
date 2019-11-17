import gameoflife.Board
import gameoflife.dsl.board
import org.hamcrest.core.IsEqual
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

    @Test fun `all cells with two neighbours survive`() {
        assertEvolution(board { O; O; O; O }, board { X; O; O; X })
    }

    private fun assertEvolution(currentGeneration: Board, nextGeneration: Board) {
        assertThat(currentGeneration.nextGeneration(), IsEqual(nextGeneration))
    }

}
