import gameoflife.Board
import gameoflife.Cell
import gameoflife.dsl.board
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test

class BoardDSLTest {

    @Test fun `creates an empty board`() {
        Assert.assertThat(board {}, IsEqual(Board()))
    }

    @Test fun `creates a board with cells`() {
        Assert.assertThat(
            board { O; X; O },
           IsEqual(Board(Cell(0).alive(), Cell(1).dead(), Cell(2).alive()))
        )
    }
}
