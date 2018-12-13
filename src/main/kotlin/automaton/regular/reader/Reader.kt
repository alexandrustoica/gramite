package automaton.regular.reader


interface Reader<out T> {
    fun read(): T
}
