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

    @Test fun `cells with less than two neighbours alive die`() {
        assertEvolution(board(
            { O; O; O; O }
        ), board(
            { X; O; O; X }
        ))
    }

    @Test fun `cells with two vertical neighbours alive survive`() {
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

    @Test fun `dead cells with two neighbours alive stay dead`() {
        assertEvolution(board(
            { O; X; O }
        ), board(
            { X; X; X }
        ))
    }

    @Test fun `cells with three neighbours alive are alive in the next generation`() {
        assertEvolution(board(
            { O; O; O },
            { X; O; X }
        ), board(
            { O; O; O },
            { O; O; O }
        ))
    }

    @Test fun `cells with more than three alive neighbours are dead in the next generation`() {
        assertEvolution(board(
            { O; O; O },
            { O; O; X },
            { X; X; X },
            { O; X; X },
            { O; O; O }
        ), board(
            { O; X; O },
            { O; X; O },
            { O; O; X },
            { O; X; X },
            { O; O; X }
        ))
    }

    private fun assertEvolution(currentGeneration: Board, nextGeneration: Board) {
        assertThat(currentGeneration.nextGeneration(), IsEqual(nextGeneration))
    }

}
