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
                Cell(1, 2).alive(), Cell(2, 3).dead(), Cell(4, 4).alive()
            )),
            IsEqual(Cells(listOf(
                Cell(1, 2).alive(), Cell(2, 3).dead(), Cell(4, 4).alive()
            )))
        )
    }

    @Test fun `unequal if other cells are in a different state`() {
        assertThat(
            Cells(listOf(
                Cell(1, 2).alive(), Cell(2, 3).dead(), Cell(4, 4).alive()
            )),
            not(IsEqual(Cells(listOf(
                Cell(1, 2).alive(), Cell(2, 3).alive(), Cell(4, 4).dead()
            ))))
        )
    }

    @Test fun `finds cells only existing in other cells`() {
        assertThat(
            Cells(listOf(
                Cell(1, 2), Cell(2, 2), Cell(3, 2), Cell(4, 2), Cell(5, 2).alive(), Cell(6, 2)
            )).onlyExistingIn(Cells(listOf(
                Cell(2, 2), Cell(4, 2), Cell(5, 2).dead(), Cell(6, 3)
            ))),
            IsEqual(Cells(listOf(
                Cell(2, 2), Cell(4, 2)
            )))
        )
    }

    @Test fun `maps each of the cells with given transformation`() {
        assertThat(
            Cells(listOf(
                Cell(1, 2).alive(), Cell(2, 2).alive(), Cell(3, 2).alive()
            )).map { it.dead() },
            IsEqual(Cells(listOf(
                Cell(1, 2).dead(), Cell(2, 2).dead(), Cell(3, 2).dead()
            )))
        )
    }
}
