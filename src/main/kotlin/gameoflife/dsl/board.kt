package gameoflife.dsl

import gameoflife.Board

fun board(grid: (BoardBuilder.() -> Unit)): Board = BoardBuilder(listOf(grid)).build()

fun board(vararg grid: (BoardBuilder.() -> Unit)): Board = BoardBuilder(grid.asList()).build()
