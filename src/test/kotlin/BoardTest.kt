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
            Cell(1).alive(), Cell(2).dead()
        ))), IsEqual(Board(Cells(listOf(
            Cell(1).alive(), Cell(2).dead()
        )))))
    }

    @Test
    fun `two boards with different cell states`() {
        Assert.assertThat(Board(Cells(listOf(
            Cell(1).alive(), Cell(2).dead()
        ))), not(IsEqual(Board(Cells(listOf(
            Cell(1).alive(), Cell(2).alive()
        )))))
        )
    }

    @Test
    fun `two boards with different cell columns`() {
        Assert.assertThat(Board(Cells(listOf(
            Cell(1).alive(), Cell(2).dead()
        ))), not(IsEqual(Board(Cells(listOf(
            Cell(1).alive(), Cell(3).dead()
        )))))
        )
    }
}
