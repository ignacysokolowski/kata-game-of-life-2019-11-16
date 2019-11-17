import gameoflife.Cell
import gameoflife.Cells
import org.hamcrest.core.IsEqual
import org.junit.Test
import org.junit.Assert.assertThat

class CellsTest {

    @Test fun `finds cells only existing in other cells`() {
        assertThat(
            Cells(listOf(
                Cell(1), Cell(2), Cell(3), Cell(4)
            )).onlyExistingIn(Cells(listOf(
                Cell(2), Cell(4), Cell(5)
            ))),
            IsEqual(Cells(listOf(
                Cell(2), Cell(4)
            )))
        )
    }
}
