package gameoflife

data class Row(val number: Int) {
    fun top() = Row(number - 1)
    fun bottom() = Row(number + 1)
}
