package automaton.regular.domain


data class Terminal(override val value: String) : Symbol {

    companion object {
        val EMPTY = Terminal("")
    }

    override fun toString(): String = value
    override val type: SymbolType = SymbolType.TERMINAL

}
