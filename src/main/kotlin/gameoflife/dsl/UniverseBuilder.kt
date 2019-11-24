package gameoflife.dsl

import gameoflife.Universe
import gameoflife.Cell
import gameoflife.Cells
import gameoflife.Column
import gameoflife.Coordinates
import gameoflife.Row

class UniverseBuilder(private val inits: List<UniverseBuilder.() -> Unit>) {
    private var cells = mutableSetOf<Cell>()
    private var coordinates = Coordinates(Column(0), Row(0))

    fun build(): Universe {
        for (init in inits) {
            init()
            nextRow()
        }
        return Universe(Cells(cells))
    }

    private fun nextRow() {
        coordinates = coordinates.movedDown().atColumn(Column(0))
    }

    val O
        get() = addCell(Cell.aliveAt(coordinates))

    val X
        get() = addCell(Cell.deadAt(coordinates))

    private fun addCell(cell: Cell) {
        cells.add(cell)
        coordinates = coordinates.movedRight()
    }
}
