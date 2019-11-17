import gameoflife.Board
import gameoflife.Cell
import gameoflife.dsl.board
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class BoardDSLTest {

    @Test fun `creates an empty board`() {
        assertThat(board {}, IsEqual(Board()))
    }

    @Test fun `creates a board with cells`() {
        assertThat(
            board { O; X; O },
            IsEqual(Board(Cell(0, 0).alive(), Cell(1, 0).dead(), Cell(2, 0).alive()))
        )
    }
}
