package automaton.regular.domain

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

data class Terminal(override val value: String) : Symbol {
    override fun toString(): String = value
    override val type: SymbolType = SymbolType.TERMINAL
    companion object {
        val EMPTY = Terminal("")
    }
}