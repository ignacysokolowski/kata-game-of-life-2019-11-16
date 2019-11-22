package gameoflife

data class Column(private val number: Int) {
    fun left() = Column(number - 1)
    fun right() = Column(number + 1)
}
