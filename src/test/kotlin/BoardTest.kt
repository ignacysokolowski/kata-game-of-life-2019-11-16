import gameoflife.Board
import gameoflife.Cell
import gameoflife.Cells
import gameoflife.Column
import gameoflife.Row
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot.not
import org.junit.Assert
import org.junit.Test

class BoardTest {
    @Test
    fun `two equal boards`() {
        Assert.assertThat(Board(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(2))
        )), IsEqual(Board(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(2))
        ))))
    }

    @Test
    fun `two boards with different cell states`() {
        Assert.assertThat(Board(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(2))
        )), not(IsEqual(Board(Cells(
            Cell.alive(Column(1), Row(2)), Cell.alive(Column(2), Row(2))
        )))))
    }

    @Test
    fun `two boards with different cell columns`() {
        Assert.assertThat(Board(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(2))
        )), not(IsEqual(Board(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(3), Row(2))
        )))))
    }

    @Test
    fun `two boards with different cell rows`() {
        Assert.assertThat(Board(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(2))
        )), not(IsEqual(Board(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(3))
        )))))
    }
}
