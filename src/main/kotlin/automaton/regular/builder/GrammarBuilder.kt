package automaton.regular.builder

import automaton.regular.domain.*


data class GrammarBuilder(
        private val terminals: List<Terminal>,
        private val nonTerminals: List<NonTerminal>,
        private val rules: List<Rule>,
        private val startSymbol: NonTerminal) {

    companion object {
        private const val REGEX_SYMBOLS = "."
    }

    constructor() : this(listOf(), listOf(), listOf(), NonTerminal(""))

    fun setTerminals(terminals: List<Terminal>): GrammarBuilder =
            GrammarBuilder(terminals, nonTerminals, rules, startSymbol)

    fun setNonTerminals(nonTerminals: List<NonTerminal>): GrammarBuilder =
            GrammarBuilder(terminals, nonTerminals, rules, startSymbol)

    fun setStartSymbol(start: NonTerminal): GrammarBuilder =
            GrammarBuilder(terminals, nonTerminals, rules, start)

    fun setRules(rules: List<String>): GrammarBuilder =
            GrammarBuilder(terminals, nonTerminals,
                    rules.map { convertStringToRule(it) }, startSymbol)

    fun build(): Grammar = Grammar(terminals, nonTerminals, rules, startSymbol)

    private fun findAllSymbolsFrom(source: String, basedOnRegex: Regex): List<String> =
            basedOnRegex.findAll(source).map { it.groupValues }
                    .map { it.first() }.toList()

    private fun convertStringToRule(string: String): Rule =
            string.replace(" ", "").split("->").let {
                Rule(convertStringToSymbols(it.component1()),
                        convertStringToSymbols(it.component2()))
            }

    private fun convertStringToSymbols(string: String): List<Symbol> =
            findAllSymbolsFrom(string, REGEX_SYMBOLS.toRegex())
                    .map { convertCharacterToSymbol(it) }

    private fun convertCharacterToSymbol(string: String): Symbol =
            if (terminals.any { it.value == string })
                Terminal(string) else NonTerminal(string)

}
