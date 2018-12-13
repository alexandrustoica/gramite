package automaton.regular.domain


interface Symbol : Comparable<Symbol> {

    val value: String
    val type: SymbolType

    fun toState() = State(value)

    override fun compareTo(other: Symbol): Int =
            value.compareTo(other.value)
}
