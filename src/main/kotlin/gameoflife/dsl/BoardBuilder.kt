package gameoflife.dsl

import gameoflife.Board
import gameoflife.Cell
import gameoflife.Cells

class BoardBuilder(private val inits: List<BoardBuilder.() -> Unit>) {
    private var cells = mutableListOf<Cell>()
    private var row = 0
    private var column = 0

    fun build(): Board {
        for (init in inits) {
            init()
            row += 1
            column = 0
        }
        return Board(Cells(cells))
    }

    val O
        get() = addCell(Cell.alive(column, row))

    val X
        get() = addCell(Cell.dead(column, row))

    private fun addCell(cell: Cell): BoardBuilder {
        cells.add(cell)
        column += 1
        return this
    }
}
