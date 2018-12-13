package automaton.regular.domain


data class State(val value: String) {
    override fun toString() = value
    fun toNonTerminal() = NonTerminal(value)
}
