import gameoflife.Cell
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertThat
import org.junit.Test

class CellTest {

    @Test fun `is alive by default`() {
        assertThat(Cell(2, 1), IsEqual(Cell(2, 1).alive()))
    }

    @Test fun `is not dead by default`() {
        assertThat(Cell(2, 1), not(IsEqual(Cell(2, 1).dead())))
    }

    @Test fun `can come to live`() {
        assertThat(Cell(2, 1).dead().alive(), IsEqual(Cell(2, 1).alive()))
    }

    @Test fun `two equal alive cells`() {
        assertThat(Cell(2, 1).alive(), IsEqual(Cell(2, 1).alive()))
    }

    @Test fun `two equal dead cells`() {
        assertThat(Cell(2, 1).dead(), IsEqual(Cell(2, 1).dead()))
    }

    @Test fun `two unequal alive cells`() {
        assertThat(Cell(2, 1).alive(), not(IsEqual(Cell(3, 1).alive())))
    }

    @Test fun `two alive cells with unequal row`() {
        assertThat(Cell(2, 1).alive(), not(IsEqual(Cell(2, 2).alive())))
    }

    @Test fun `two unequal dead cells`() {
        assertThat(Cell(2, 1).dead(), not(IsEqual(Cell(3, 1).dead())))
    }

    @Test fun `two dead cells with unequal row`() {
        assertThat(Cell(2, 1).dead(), not(IsEqual(Cell(2, 2).dead())))
    }

    @Test fun `has neighbours on the left and right`() {
        assertThat(Cell(1, 1).neighbours(), IsEqual(listOf(Cell(0, 1), Cell(2, 1))))
        assertThat(Cell(2, 2).neighbours(), IsEqual(listOf(Cell(1, 2), Cell(3, 2))))
    }

    @Test fun `dies without neighbours`() {
        assertThat(Cell(1, 2).alive().nextGenerationGivenNeighbours(0), IsEqual(Cell(1, 2).dead()))
    }

    @Test fun `dies with one neighbour`() {
        assertThat(Cell(1, 2).alive().nextGenerationGivenNeighbours(1), IsEqual(Cell(1, 2).dead()))
    }

    @Test fun `survives with two neighbours`() {
        assertThat(Cell(1, 2).alive().nextGenerationGivenNeighbours(2), IsEqual(Cell(1, 2).alive()))
    }
}
