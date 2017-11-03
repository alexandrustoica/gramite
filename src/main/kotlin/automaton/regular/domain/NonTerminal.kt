package automaton.regular.domain

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

data class NonTerminal(override val value: String) : Symbol {
    override fun toString(): String = value
    override val type: SymbolType = SymbolType.NONTERMINAL
}