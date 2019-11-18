import gameoflife.Cell
import gameoflife.Cells
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot.not
import org.junit.Test
import org.junit.Assert.assertThat

class CellsTest {

    @Test fun `equal if has the some cells as the other one`() {
        assertThat(
            Cells(listOf(
                Cell.alive(1, 2), Cell.dead(2, 3), Cell.alive(4, 4)
            )),
            IsEqual(Cells(listOf(
                Cell.alive(1, 2), Cell.dead(2, 3), Cell.alive(4, 4)
            )))
        )
    }

    @Test fun `unequal if other cells are in a different state`() {
        assertThat(
            Cells(listOf(
                Cell.alive(1, 2), Cell.dead(2, 3), Cell.alive(4, 4)
            )),
            not(IsEqual(Cells(listOf(
                Cell.alive(1, 2), Cell.alive(2, 3), Cell.dead(4, 4)
            ))))
        )
    }

    @Test fun `finds cells only existing in other cells`() {
        assertThat(
            Cells(listOf(
                Cell.alive(1, 2),
                Cell.alive(2, 2),
                Cell.alive(3, 2),
                Cell.alive(4, 2),
                Cell.alive(5, 2),
                Cell.alive(6, 2)
            )).onlyExistingIn(Cells(listOf(
                Cell.alive(2, 2),
                Cell.alive(4, 2),
                Cell.dead(5, 2),
                Cell.alive(6, 3)
            ))),
            IsEqual(Cells(listOf(
                Cell.alive(2, 2),
                Cell.alive(4, 2)
            )))
        )
    }

    @Test fun `maps each of the cells with given transformation`() {
        assertThat(
            Cells(listOf(
                Cell.alive(1, 2), Cell.alive(2, 2), Cell.alive(3, 2)
            )).map { it.dead() },
            IsEqual(Cells(listOf(
                Cell.dead(1, 2), Cell.dead(2, 2), Cell.dead(3, 2)
            )))
        )
    }
}
