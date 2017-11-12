package automaton.regular.adapter

import automaton.regular.domain.*

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

class AutomatonAdapter(private val grammar: Grammar) {

    fun toAutomaton() =
            FiniteAutomaton(states(), grammar.startSymbol.toState(), endStates(), grammar.terminals, transitions())

    private fun transitions() =
            grammar.rules.groupBy { it.type }.let {
                convertEmptyRulesToTransitions(it[Rule.RuleType.EMPTY]) +
                        convertTerminalRulesToTransitions(it[Rule.RuleType.TERMINAL]) +
                        convertNonTerminalRulesToTransitions(it[Rule.RuleType.NONTERMINAL])
            }

    private fun convertTerminalRulesToTransitions(rules: List<Rule>?) =
            rules?.mapIndexed { index, rule ->
                Transition(rule.head.first().toState(),
                        generatedStates[index], Terminal(rule.body.first().value))
            } ?: listOf()

    private fun convertEmptyRulesToTransitions(rules: List<Rule>?) =
            rules?.map {
                Transition(it.head.first().toState(), it.head.first().toState(),
                        Terminal(""))
            } ?: listOf()

    private fun convertNonTerminalRulesToTransitions(rules: List<Rule>?) =
            rules?.map {
                Transition(it.head.first().toState(), it.body.component2().toState(),
                        Terminal(it.body.first().value))
            } ?: listOf()

    private fun endStates() = generatedStates +
            if (isStartSymbolEndState()) listOf(grammar.startSymbol.toState()) else listOf()

    private fun states() = generatedStates +
            grammar.nonTerminals.map { State(it.value) }.toList()

    private val generatedStates: List<State> = grammar.rules
            .filter { it.body.size == 1 && it.body.first().type == SymbolType.TERMINAL }
            .mapIndexed { index, _ -> State("W$index") }

    private fun isStartSymbolEndState(): Boolean =
            grammar.rules.any { it.head.first() == grammar.startSymbol && it.body.isEmpty() }

}
