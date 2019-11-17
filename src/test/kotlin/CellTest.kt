import gameoflife.Cell
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot
import org.junit.Assert.assertThat
import org.junit.Test

class CellTest {

    @Test fun `is alive by default`() {
        assertThat(Cell(2), IsEqual(Cell(2).alive()))
    }

    @Test fun `is not dead by default`() {
        assertThat(Cell(2), IsNot.not(IsEqual(Cell(2).dead())))
    }

    @Test fun `can come to live`() {
        assertThat(Cell(2).dead().alive(), IsEqual(Cell(2).alive()))
    }

    @Test fun `two equal alive cells`() {
        assertThat(Cell(2).alive(), IsEqual(Cell(2).alive()))
    }

    @Test fun `two equal dead cells`() {
        assertThat(Cell(2).dead(), IsEqual(Cell(2).dead()))
    }

    @Test fun `two unequal alive cells`() {
        assertThat(Cell(2).alive(), IsNot.not(IsEqual(Cell(3).alive())))
    }

    @Test fun `two unequal dead cells`() {
        assertThat(Cell(2).dead(), IsNot.not(IsEqual(Cell(3).dead())))
    }

    @Test fun `has neighbours on the left and right`() {
        assertThat(Cell(1).neighbours(), IsEqual(setOf(Cell(0), Cell(2))))
        assertThat(Cell(2).neighbours(), IsEqual(setOf(Cell(1), Cell(3))))
    }

    @Test fun `dies without neighbours`() {
        assertThat(Cell(1).alive().nextGenerationGivenNeighbours(0), IsEqual(Cell(1).dead()))
    }
}
