package gameoflife.dsl

import gameoflife.Board
import gameoflife.Cell
import gameoflife.Cells

class BoardBuilder(private val init: BoardBuilder.() -> Unit) {
    private var cells = mutableListOf<Cell>()
    private var column = 0

    fun build(): Board {
        init()
        return Board(Cells(cells))
    }

    val O
        get() = addCell(nextCell().alive())

    val X
        get() = addCell(nextCell().dead())

    private fun nextCell() = Cell(column)

    private fun addCell(cell: Cell): BoardBuilder {
        cells.add(cell)
        column += 1
        return this
    }
}
