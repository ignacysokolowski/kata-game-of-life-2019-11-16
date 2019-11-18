import gameoflife.Cell
import gameoflife.Cells
import gameoflife.Column
import gameoflife.Row
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot.not
import org.junit.Test
import org.junit.Assert.assertThat

class CellsTest {

    @Test fun `equal if has the some cells as the other one`() {
        assertThat(
            Cells(
                Cell.alive(Column(1), Row(2)),
                Cell.dead(Column(2), Row(3)),
                Cell.alive(Column(4), Row(4))
            ),
            IsEqual(Cells(
                Cell.alive(Column(1), Row(2)),
                Cell.dead(Column(2), Row(3)),
                Cell.alive(Column(4), Row(4))
            ))
        )
    }

    @Test fun `unequal if other cells are in a different state`() {
        assertThat(
            Cells(
                Cell.alive(Column(1), Row(2)),
                Cell.dead(Column(2), Row(3)),
                Cell.alive(Column(4), Row(4))
            ),
            not(IsEqual(Cells(
                Cell.alive(Column(1), Row(2)),
                Cell.alive(Column(2), Row(3)),
                Cell.dead(Column(4), Row(4))
            )))
        )
    }

    @Test fun `finds cells only existing in other cells`() {
        assertThat(
            Cells(
                Cell.alive(Column(1), Row(2)),
                Cell.alive(Column(2), Row(2)),
                Cell.alive(Column(3), Row(2)),
                Cell.alive(Column(4), Row(2)),
                Cell.alive(Column(5), Row(2)),
                Cell.alive(Column(6), Row(2))
            ).onlyExistingIn(Cells(
                Cell.alive(Column(2), Row(2)),
                Cell.alive(Column(4), Row(2)),
                Cell.dead(Column(5), Row(2)),
                Cell.alive(Column(6), Row(3))
            )),
            IsEqual(Cells(
                Cell.alive(Column(2), Row(2)),
                Cell.alive(Column(4), Row(2))
            ))
        )
    }

    @Test fun `maps each of the cells with given transformation`() {
        assertThat(
            Cells(
                Cell.alive(Column(1), Row(2)),
                Cell.alive(Column(2), Row(2)),
                Cell.alive(Column(3), Row(2))
            ).map { it.dead() },
            IsEqual(Cells(
                Cell.dead(Column(1), Row(2)),
                Cell.dead(Column(2), Row(2)),
                Cell.dead(Column(3), Row(2))
            ))
        )
    }
}
