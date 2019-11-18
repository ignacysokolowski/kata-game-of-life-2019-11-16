import gameoflife.Board
import gameoflife.Cell
import gameoflife.dsl.board
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class BoardDSLTest {

    @Test fun `creates an empty board`() {
        assertThat(board(), IsEqual(Board()))
    }

    @Test fun `creates a board with cells`() {
        assertThat(
            board(
                { O; X; O },
                { X; O; X },
                { O; O; X }
            ),
            IsEqual(Board(
                Cell.alive(0, 0), Cell.dead(1, 0), Cell.alive(2, 0),
                Cell.dead(0, 1), Cell.alive(1, 1), Cell.dead(2, 1),
                Cell.alive(0, 2), Cell.alive(1, 2), Cell.dead(2, 2)
            ))
        )
    }
}
