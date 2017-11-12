package automaton.regular.domain


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

data class State(val value: String) {
    override fun toString() = value
    companion object {
        val EMPTY = State("")
    }
    fun toNonTerminal() = NonTerminal(value)
    fun toTerminal() = Terminal(value)
}