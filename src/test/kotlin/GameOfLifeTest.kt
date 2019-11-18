import gameoflife.Board
import gameoflife.dsl.board
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class GameOfLifeTest {

    @Test fun `empty board stays empty in the next generation`() {
        assertEvolution(board(), board())
    }

    @Test fun `all cells die in a board with a single cell`() {
        assertEvolution(board(
            { O }
        ), board(
            { X }
        ))
    }

    @Test fun `dead cell with two neighbours alive stays dead`() {
        assertEvolution(board(
            { O; X; O }
        ), board(
            { X; X; X }
        ))
    }

    @Test fun `all cells with two horizontal neighbours alive survive`() {
        assertEvolution(board(
            { O; O; O; O }
        ), board(
            { X; O; O; X }
        ))
    }

    @Test fun `all cells with two vertical neighbours alive survive`() {
        assertEvolution(board(
            { O },
            { O },
            { O },
            { O }
        ), board(
            { X },
            { O },
            { O },
            { X }
        ))
    }

    @Test fun `cell with less than two neighbours alive dies`() {
        assertEvolution(board(
            { X; O; X }
        ), board(
            { X; X; X }
        ))
    }

    private fun assertEvolution(currentGeneration: Board, nextGeneration: Board) {
        assertThat(currentGeneration.nextGeneration(), IsEqual(nextGeneration))
    }

}
