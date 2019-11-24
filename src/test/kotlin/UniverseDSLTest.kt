import gameoflife.Universe
import gameoflife.Cell
import gameoflife.Cells
import gameoflife.Column
import gameoflife.Row
import gameoflife.dsl.universe
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class UniverseDSLTest {

    @Test fun `creates an empty universe`() {
        assertThat(universe(), IsEqual(Universe(Cells())))
    }

    @Test fun `creates a universe with cells`() {
        assertThat(
            universe(
                { O; X; O },
                { X; O; X },
                { O; O; X }
            ),
            IsEqual(Universe(Cells(
                Cell.alive(Column(0), Row(0)), Cell.dead(Column(1), Row(0)), Cell.alive(Column(2), Row(0)),
                Cell.dead(Column(0), Row(1)), Cell.alive(Column(1), Row(1)), Cell.dead(Column(2), Row(1)),
                Cell.alive(Column(0), Row(2)), Cell.alive(Column(1), Row(2)), Cell.dead(Column(2), Row(2))
            )))
        )
    }
}
