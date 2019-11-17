package gameoflife

class Cells(private val cells: List<Cell>) {
    fun size() = cells.size

    fun allDead() = Cells(cells.map { it.dead() })

    fun onlyExistingIn(other: Cells) =
        Cells(cells.filter { other.cells.contains(it) })

    override fun toString() = "Cells(${cells})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cells) return false
        return other.cells == cells
    }

    override fun hashCode() = javaClass.hashCode()
}
