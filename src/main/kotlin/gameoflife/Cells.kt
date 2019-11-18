package gameoflife

class Cells constructor(private val cells: Set<Cell>) {
    constructor(vararg cells: Cell) : this(cells.toSet())
    private constructor(cells: List<Cell>) : this(cells.toSet())

    fun size() = cells.size

    fun map(transform: (Cell) -> Cell) = Cells(cells.map(transform))

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
