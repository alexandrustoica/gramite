package automaton.regular.domain

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

data class Rule(val head: List<Symbol>, val body: List<Symbol>) {

    enum class RuleType {
        EMPTY, TERMINAL, NONTERMINAL
    }

    companion object Converter {
        private val asString = fun List<Symbol>.(): String =
                this.map { it.value }.reduce { accumulator, item -> accumulator + item }
    }

    val type: Rule.RuleType =
            if (body.isEmpty()) Rule.RuleType.EMPTY
            else if (body.size == 1 && body.component1().type == SymbolType.TERMINAL) Rule.RuleType.TERMINAL
            else Rule.RuleType.NONTERMINAL

    override fun toString() =
            head.asString() + " -> " + body.asString()

    fun isRegular(): Boolean = regularHead && regularBody

    private val regularHead: Boolean = head.size == 1 && head.all { it.type == SymbolType.NONTERMINAL }

    private val regularBody: Boolean = body.size <= 2 &&
            body.getOrNull(0)?.type ?: SymbolType.TERMINAL == SymbolType.TERMINAL &&
            body.getOrNull(1)?.type ?: SymbolType.NONTERMINAL == SymbolType.NONTERMINAL

}
