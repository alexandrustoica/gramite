package automata.regular.domain

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

data class Grammar(val terminals: List<Terminal>,
                   val nonTerminals: List<NonTerminal>,
                   val rules: List<Rule>,
                   val startSymbol: NonTerminal) {
    constructor() : this(listOf(), listOf(), listOf(), NonTerminal(""))

    fun isRegular(): Boolean = rules.all { it.isRegular() }

    fun getRulesFor(nonTerminal: NonTerminal): List<Rule> =
            rules.filter{ it.head.contains(nonTerminal) }
}
