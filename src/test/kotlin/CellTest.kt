import gameoflife.Cell
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertThat
import org.junit.Test

class CellTest {

    @Test fun `can come to live`() {
        assertThat(Cell.dead(2, 1).alive(), IsEqual(Cell.alive(2, 1)))
    }

    @Test fun `can die`() {
        assertThat(Cell.alive(2, 1).dead(), IsEqual(Cell.dead(2, 1)))
    }

    @Test fun `alive cell is not dead`() {
        assertThat(Cell.alive(2, 1), not(IsEqual(Cell.dead(2, 1))))
    }

    @Test fun `two equal alive cells`() {
        assertThat(Cell.alive(2, 1), IsEqual(Cell.alive(2, 1)))
    }

    @Test fun `two equal dead cells`() {
        assertThat(Cell.dead(2, 1), IsEqual(Cell.dead(2, 1)))
    }

    @Test fun `two unequal alive cells`() {
        assertThat(Cell.alive(2, 1), not(IsEqual(Cell.alive(3, 1))))
    }

    @Test fun `two alive cells with unequal row`() {
        assertThat(Cell.alive(2, 1), not(IsEqual(Cell.alive(2, 2))))
    }

    @Test fun `two unequal dead cells`() {
        assertThat(Cell.dead(2, 1), not(IsEqual(Cell.dead(3, 1))))
    }

    @Test fun `two dead cells with unequal row`() {
        assertThat(Cell.dead(2, 1), not(IsEqual(Cell.dead(2, 2))))
    }

    @Test fun `has potential alive neighbours horizontally, vertically and diagonally`() {
        assertThat(
            Cell.alive(2, 2).potentialAliveNeighbours(),
            IsEqual(listOf(
                Cell.alive(1, 1),
                Cell.alive(2, 1),
                Cell.alive(3, 1),
                Cell.alive(3, 2),
                Cell.alive(3, 3),
                Cell.alive(2, 3),
                Cell.alive(1, 3),
                Cell.alive(1, 2)
            ))
        )
        assertThat(
            Cell.alive(3, 3).potentialAliveNeighbours(),
            IsEqual(listOf(
                Cell.alive(2, 2),
                Cell.alive(3, 2),
                Cell.alive(4, 2),
                Cell.alive(4, 3),
                Cell.alive(4, 4),
                Cell.alive(3, 4),
                Cell.alive(2, 4),
                Cell.alive(2, 3)
            ))
        )
    }

    @Test fun `alive cell dies without alive neighbours`() {
        assertThat(Cell.alive(1, 2).nextGenerationGiven(neighboursAlive = 0), IsEqual(Cell.dead(1, 2)))
    }

    @Test fun `alive cell dies with one alive neighbour`() {
        assertThat(Cell.alive(1, 2).nextGenerationGiven(neighboursAlive = 1), IsEqual(Cell.dead(1, 2)))
    }

    @Test fun `alive cell survives with two alive neighbours`() {
        assertThat(Cell.alive(1, 2).nextGenerationGiven(neighboursAlive = 2), IsEqual(Cell.alive(1, 2)))
    }

    @Test fun `dead cell stays dead without alive neighbours`() {
        assertThat(Cell.dead(1, 2).nextGenerationGiven(neighboursAlive = 0), IsEqual(Cell.dead(1, 2)))
    }

    @Test fun `dead cell stays dead with one alive neighbour`() {
        assertThat(Cell.dead(1, 2).nextGenerationGiven(neighboursAlive = 1), IsEqual(Cell.dead(1, 2)))
    }

    @Test fun `dead cell stays dead with two alive neighbours`() {
        assertThat(Cell.dead(1, 2).nextGenerationGiven(neighboursAlive = 2), IsEqual(Cell.dead(1, 2)))
    }

    @Test fun `dead cell comes to life with three alive neighbours`() {
        assertThat(Cell.dead(1, 2).nextGenerationGiven(neighboursAlive = 3), IsEqual(Cell.alive(1, 2)))
    }
}
