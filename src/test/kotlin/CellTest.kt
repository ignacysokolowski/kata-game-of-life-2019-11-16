import gameoflife.Cell
import gameoflife.Column
import gameoflife.Row
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertThat
import org.junit.Test

class CellTest {

    @Test fun `can come to live`() {
        assertThat(Cell.dead(Column(2), Row(1)).alive(), IsEqual(Cell.alive(Column(2), Row(1))))
    }

    @Test fun `can die`() {
        assertThat(Cell.alive(Column(2), Row(1)).dead(), IsEqual(Cell.dead(Column(2), Row(1))))
    }

    @Test fun `alive cell is not dead`() {
        assertThat(Cell.alive(Column(2), Row(1)), not(IsEqual(Cell.dead(Column(2), Row(1)))))
    }

    @Test fun `two equal alive cells`() {
        assertThat(Cell.alive(Column(2), Row(1)), IsEqual(Cell.alive(Column(2), Row(1))))
    }

    @Test fun `two equal dead cells`() {
        assertThat(Cell.dead(Column(2), Row(1)), IsEqual(Cell.dead(Column(2), Row(1))))
    }

    @Test fun `two unequal alive cells`() {
        assertThat(Cell.alive(Column(2), Row(1)), not(IsEqual(Cell.alive(Column(3), Row(1)))))
    }

    @Test fun `two alive cells with unequal row`() {
        assertThat(Cell.alive(Column(2), Row(1)), not(IsEqual(Cell.alive(Column(2), Row(2)))))
    }

    @Test fun `two unequal dead cells`() {
        assertThat(Cell.dead(Column(2), Row(1)), not(IsEqual(Cell.dead(Column(3), Row(1)))))
    }

    @Test fun `two dead cells with unequal row`() {
        assertThat(Cell.dead(Column(2), Row(1)), not(IsEqual(Cell.dead(Column(2), Row(2)))))
    }

    @Test fun `has potential alive neighbours horizontally, vertically and diagonally`() {
        assertThat(
            Cell.alive(Column(2), Row(2)).potentialAliveNeighbours(),
            IsEqual(setOf(
                Cell.alive(Column(1), Row(1)),
                Cell.alive(Column(2), Row(1)),
                Cell.alive(Column(3), Row(1)),
                Cell.alive(Column(1), Row(2)),
                Cell.alive(Column(3), Row(2)),
                Cell.alive(Column(1), Row(3)),
                Cell.alive(Column(2), Row(3)),
                Cell.alive(Column(3), Row(3))
            ))
        )
        assertThat(
            Cell.alive(Column(3), Row(3)).potentialAliveNeighbours(),
            IsEqual(setOf(
                Cell.alive(Column(2), Row(2)),
                Cell.alive(Column(3), Row(2)),
                Cell.alive(Column(4), Row(2)),
                Cell.alive(Column(2), Row(3)),
                Cell.alive(Column(4), Row(3)),
                Cell.alive(Column(2), Row(4)),
                Cell.alive(Column(3), Row(4)),
                Cell.alive(Column(4), Row(4))
            ))
        )
    }

    @Test fun `alive cell dies without alive neighbours`() {
        assertThat(
            Cell.alive(Column(1), Row(2)).nextGenerationGiven(neighboursAlive = 0),
            IsEqual(Cell.dead(Column(1), Row(2)))
        )
    }

    @Test fun `alive cell dies with one alive neighbour`() {
        assertThat(
            Cell.alive(Column(1), Row(2)).nextGenerationGiven(neighboursAlive = 1),
            IsEqual(Cell.dead(Column(1), Row(2)))
        )
    }

    @Test fun `alive cell survives with two alive neighbours`() {
        assertThat(
            Cell.alive(Column(1), Row(2)).nextGenerationGiven(neighboursAlive = 2),
            IsEqual(Cell.alive(Column(1), Row(2)))
        )
    }

    @Test fun `alive cell survives with three alive neighbours`() {
        assertThat(
            Cell.alive(Column(1), Row(2)).nextGenerationGiven(neighboursAlive = 3),
            IsEqual(Cell.alive(Column(1), Row(2)))
        )
    }

    @Test fun `alive cell dies with more than three alive neighbours`() {
        assertThat(
            Cell.alive(Column(1), Row(2)).nextGenerationGiven(neighboursAlive = 4),
            IsEqual(Cell.dead(Column(1), Row(2)))
        )
    }

    @Test fun `dead cell stays dead without alive neighbours`() {
        assertThat(
            Cell.dead(Column(1), Row(2)).nextGenerationGiven(neighboursAlive = 0),
            IsEqual(Cell.dead(Column(1), Row(2)))
        )
    }

    @Test fun `dead cell stays dead with one alive neighbour`() {
        assertThat(
            Cell.dead(Column(1), Row(2)).nextGenerationGiven(neighboursAlive = 1),
            IsEqual(Cell.dead(Column(1), Row(2)))
        )
    }

    @Test fun `dead cell stays dead with two alive neighbours`() {
        assertThat(
            Cell.dead(Column(1), Row(2)).nextGenerationGiven(neighboursAlive = 2),
            IsEqual(Cell.dead(Column(1), Row(2)))
        )
    }

    @Test fun `dead cell comes to life with three alive neighbours`() {
        assertThat(
            Cell.dead(Column(1), Row(2)).nextGenerationGiven(neighboursAlive = 3),
            IsEqual(Cell.alive(Column(1), Row(2)))
        )
    }

    @Test fun `dead cell stays dead with more than three alive neighbours`() {
        assertThat(
            Cell.dead(Column(1), Row(2)).nextGenerationGiven(neighboursAlive = 4),
            IsEqual(Cell.dead(Column(1), Row(2)))
        )
    }
}
