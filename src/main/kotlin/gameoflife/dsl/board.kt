package gameoflife.dsl

import gameoflife.Board

fun board(grid: (BoardBuilder.() -> Unit)): Board = BoardBuilder(grid).build()
