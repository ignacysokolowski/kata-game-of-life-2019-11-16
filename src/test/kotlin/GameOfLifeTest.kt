import gameoflife.Universe
import gameoflife.dsl.universe
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class GameOfLifeTest {

    @Test fun `empty universe stays empty in the next generation`() {
        assertEvolution(universe(), universe())
    }

    @Test fun `all cells die in a universe with a single cell`() {
        assertEvolution(universe(
            { O }
        ), universe(
            { X }
        ))
    }

    @Test fun `cells with less than two neighbours alive die`() {
        assertEvolution(universe(
            { O; O; O; O }
        ), universe(
            { X; O; O; X }
        ))
    }

    @Test fun `cells with two vertical neighbours alive survive`() {
        assertEvolution(universe(
            { O },
            { O },
            { O },
            { O }
        ), universe(
            { X },
            { O },
            { O },
            { X }
        ))
    }

    @Test fun `dead cells with two neighbours alive stay dead`() {
        assertEvolution(universe(
            { O; X; O }
        ), universe(
            { X; X; X }
        ))
    }

    @Test fun `cells with three neighbours alive are alive in the next generation`() {
        assertEvolution(universe(
            { O; O; O },
            { X; O; X }
        ), universe(
            { O; O; O },
            { O; O; O }
        ))
    }

    @Test fun `cells with more than three alive neighbours are dead in the next generation`() {
        assertEvolution(universe(
            { O; O; O },
            { O; O; X },
            { X; X; X },
            { O; X; X },
            { O; O; O }
        ), universe(
            { O; X; O },
            { O; X; O },
            { O; O; X },
            { O; X; X },
            { O; O; X }
        ))
    }

    @Test fun `block is a still life`() {
        assertGenerations(universe(
            { X; X; X; X },
            { X; O; O; X },
            { X; O; O; X },
            { X; X; X; X }
        ), universe(
            { X; X; X; X },
            { X; O; O; X },
            { X; O; O; X },
            { X; X; X; X }
        ))
    }

    @Test fun `blinker is an oscillator`() {
        assertGenerations(universe(
            { X; X; X },
            { O; O; O },
            { X; X; X }
        ), universe(
            { X; O; X },
            { X; O; X },
            { X; O; X }
        ), universe(
            { X; X; X },
            { O; O; O },
            { X; X; X }
        ), universe(
            { X; O; X },
            { X; O; X },
            { X; O; X }
        ))
    }

    @Test fun `glider is a spaceship`() {
        assertGenerations(universe(
            { X; O; X; X },
            { X; X; O; X },
            { O; O; O; X },
            { X; X; X; X }
        ), universe(
            { X; X; X; X },
            { O; X; O; X },
            { X; O; O; X },
            { X; O; X; X }
        ), universe(
            { X; X; X; X },
            { X; X; O; X },
            { O; X; O; X },
            { X; O; O; X }
        ), universe(
            { X; X; X; X },
            { X; O; X; X },
            { X; X; O; O },
            { X; O; O; X }
        ))
    }

    private fun assertEvolution(currentGeneration: Universe, nextGeneration: Universe) {
        assertThat(currentGeneration.nextGeneration(), IsEqual(nextGeneration))
    }

    private fun assertGenerations(vararg generations: Universe) {
        var currentGeneration: Universe? = null
        for (nextGeneration in generations) {
            if (currentGeneration != null) {
                assertEvolution(currentGeneration, nextGeneration)
            }
            currentGeneration = nextGeneration
        }
    }

}
