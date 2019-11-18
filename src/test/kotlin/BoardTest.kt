import gameoflife.Board
import gameoflife.Cell
import gameoflife.Cells
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot.not
import org.junit.Assert
import org.junit.Test

class BoardTest {
    @Test
    fun `two equal boards`() {
        Assert.assertThat(Board(Cells(listOf(
            Cell.alive(1, 2), Cell.dead(2, 2)
        ))), IsEqual(Board(Cells(listOf(
            Cell.alive(1, 2), Cell.dead(2, 2)
        )))))
    }

    @Test
    fun `two boards with different cell states`() {
        Assert.assertThat(Board(Cells(listOf(
            Cell.alive(1, 2), Cell.dead(2, 2)
        ))), not(IsEqual(Board(Cells(listOf(
            Cell.alive(1, 2), Cell.alive(2, 2)
        )))))
        )
    }

    @Test
    fun `two boards with different cell columns`() {
        Assert.assertThat(Board(Cells(listOf(
            Cell.alive(1, 2), Cell.dead(2, 2)
        ))), not(IsEqual(Board(Cells(listOf(
            Cell.alive(1, 2), Cell.dead(3, 2)
        )))))
        )
    }

    @Test
    fun `two boards with different cell rows`() {
        Assert.assertThat(Board(Cells(listOf(
            Cell.alive(1, 2), Cell.dead(2, 2)
        ))), not(IsEqual(Board(Cells(listOf(
            Cell.alive(1, 2), Cell.dead(2, 3)
        )))))
        )
    }
}
