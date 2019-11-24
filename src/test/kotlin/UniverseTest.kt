import gameoflife.Universe
import gameoflife.Cell
import gameoflife.Cells
import gameoflife.Column
import gameoflife.Row
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot.not
import org.junit.Assert
import org.junit.Test

class UniverseTest {
    @Test
    fun `two equal universes`() {
        Assert.assertThat(Universe(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(2))
        )), IsEqual(Universe(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(2))
        ))))
    }

    @Test
    fun `two universes with different cell states`() {
        Assert.assertThat(Universe(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(2))
        )), not(IsEqual(Universe(Cells(
            Cell.alive(Column(1), Row(2)), Cell.alive(Column(2), Row(2))
        )))))
    }

    @Test
    fun `two universes with different cell columns`() {
        Assert.assertThat(Universe(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(2))
        )), not(IsEqual(Universe(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(3), Row(2))
        )))))
    }

    @Test
    fun `two universes with different cell rows`() {
        Assert.assertThat(Universe(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(2))
        )), not(IsEqual(Universe(Cells(
            Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(3))
        )))))
    }
}
