package gameoflife.dsl

import gameoflife.Board
import gameoflife.Cell
import gameoflife.Cells
import gameoflife.Column
import gameoflife.Coordinates
import gameoflife.Row

class BoardBuilder(private val inits: List<BoardBuilder.() -> Unit>) {
    private var cells = mutableSetOf<Cell>()
    private var coordinates = Coordinates(Column(0), Row(0))

    fun build(): Board {
        for (init in inits) {
            init()
            nextRow()
        }
        return Board(Cells(cells))
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
