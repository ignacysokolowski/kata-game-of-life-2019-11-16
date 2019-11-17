package gameoflife.dsl

import gameoflife.Board

fun board(vararg grid: (BoardBuilder.() -> Unit)): Board = BoardBuilder(grid.asList()).build()
