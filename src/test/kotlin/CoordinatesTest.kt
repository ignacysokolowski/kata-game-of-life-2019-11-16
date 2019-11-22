import gameoflife.Column
import gameoflife.Coordinates
import gameoflife.Row
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertThat
import org.junit.Test

class CoordinatesTest {

    @Test fun `two different pairs of coordinates`() {
        assertThat(
            Coordinates(Column(2), Row(5)),
            not(IsEqual(Coordinates(Column(1), Row(2))))
        )
    }

    @Test fun `can be moved one column to the right`() {
        assertThat(
            Coordinates(Column(2), Row(5)).movedRight(),
            IsEqual(Coordinates(Column(3), Row(5)))
        )
    }

    @Test fun `can be moved one column to the left`() {
        assertThat(
            Coordinates(Column(2), Row(5)).movedLeft(),
            IsEqual(Coordinates(Column(1), Row(5)))
        )
    }

    @Test fun `can be moved one row up`() {
        assertThat(
            Coordinates(Column(2), Row(5)).movedUp(),
            IsEqual(Coordinates(Column(2), Row(4)))
        )
    }

    @Test fun `can be moved one row down`() {
        assertThat(
            Coordinates(Column(2), Row(5)).movedDown(),
            IsEqual(Coordinates(Column(2), Row(6)))
        )
    }

    @Test fun `can be moved to a specific column`() {
        assertThat(
            Coordinates(Column(2), Row(5)).atColumn(Column(1)),
            IsEqual(Coordinates(Column(1), Row(5)))
        )
    }
}
