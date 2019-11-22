package gameoflife

data class Row(private val number: Int) {
    fun top() = Row(number - 1)
    fun bottom() = Row(number + 1)
}
