import gameoflife.Cell
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot
import org.junit.Assert
import org.junit.Test

class CellTest {

    @Test fun `is alive by default`() {
        Assert.assertThat(Cell(2), IsEqual(Cell(2).alive()))
    }

    @Test fun `is not dead by default`() {
        Assert.assertThat(Cell(2), IsNot.not(IsEqual(Cell(2).dead())))
    }

    @Test fun `can come to live`() {
        Assert.assertThat(Cell(2).dead().alive(), IsEqual(Cell(2).alive()))
    }

    @Test fun `two equal alive cells`() {
        Assert.assertThat(Cell(2).alive(), IsEqual(Cell(2).alive()))
    }

    @Test fun `two equal dead cells`() {
        Assert.assertThat(Cell(2).dead(), IsEqual(Cell(2).dead()))
    }

    @Test fun `two unequal alive cells`() {
        Assert.assertThat(Cell(2).alive(), IsNot.not(IsEqual(Cell(3).alive())))
    }

    @Test fun `two unequal dead cells`() {
        Assert.assertThat(Cell(2).dead(), IsNot.not(IsEqual(Cell(3).dead())))
    }

    @Test fun `has neighbours on the left and right`() {
        Assert.assertThat(Cell(1).neighbours(), IsEqual(setOf(Cell(0), Cell(2))))
        Assert.assertThat(Cell(2).neighbours(), IsEqual(setOf(Cell(1), Cell(3))))
    }
}
