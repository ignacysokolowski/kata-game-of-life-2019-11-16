import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class GameOfLifeTest {

    @Test fun `empty board stays empty in the next generation`() {
        assertThat(Board().nextGeneration(), IsEqual(Board()))
    }
}

class Board {
    fun nextGeneration(): Board {
        return Board()
    }

    override fun toString(): String {
        return "Board()"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return javaClass == other?.javaClass
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
