import gameoflife.Board
import gameoflife.Cell
import gameoflife.Column
import gameoflife.Row
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
                Cell.alive(Column(0), Row(0)), Cell.dead(Column(1), Row(0)), Cell.alive(Column(2), Row(0)),
                Cell.dead(Column(0), Row(1)), Cell.alive(Column(1), Row(1)), Cell.dead(Column(2), Row(1)),
                Cell.alive(Column(0), Row(2)), Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(2))
            ))
        )
    }
}
