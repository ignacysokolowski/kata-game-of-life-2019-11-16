package gameoflife.dsl

import gameoflife.Board
import gameoflife.Cell
import gameoflife.Cells
import gameoflife.Column
import gameoflife.Row

class BoardBuilder(private val inits: List<BoardBuilder.() -> Unit>) {
    private var cells = mutableSetOf<Cell>()
    private var row = Row(0)
    private var column = Column(0)

    fun build(): Board {
        for (init in inits) {
            init()
            nextRow()
        }
        return Board(Cells(cells))
    }

    private fun nextRow() {
        row = row.next()
        column = Column(0)
    }

    val O
        get() = addCell(Cell.alive(column, row))

    val X
        get() = addCell(Cell.dead(column, row))

    private fun addCell(cell: Cell) {
        cells.add(cell)
        column = column.next()
    }
}
