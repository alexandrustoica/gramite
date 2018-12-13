package automaton.regular.domain


data class NonTerminal(override val value: String) : Symbol {
    override fun toString(): String = value
    override val type: SymbolType = SymbolType.NONTERMINAL
}
