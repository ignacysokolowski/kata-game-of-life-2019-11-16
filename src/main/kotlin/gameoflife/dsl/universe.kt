package gameoflife.dsl

import gameoflife.Universe

fun universe(vararg grid: (UniverseBuilder.() -> Unit)): Universe = UniverseBuilder(grid.asList()).build()
