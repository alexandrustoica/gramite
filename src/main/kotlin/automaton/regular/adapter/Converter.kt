package automaton.regular.adapter

import automaton.regular.builder.FiniteAutomatonBuilder
import automaton.regular.builder.GrammarBuilder
import automaton.regular.domain.*

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

class Converter {

    fun toGrammar(automaton: FiniteAutomaton): Grammar = GrammarBuilder()
            .setNonTerminals(automaton.states.map { NonTerminal(it.value) })
            .setTerminals(automaton.alphabet)
            .setStartSymbol(NonTerminal(automaton.startState.value))
            .build()

    fun toAutomaton(grammar: Grammar): FiniteAutomaton = FiniteAutomatonBuilder()
            .setStates(grammar.nonTerminals.map { State(it.value) })
            .setAlphabet(grammar.terminals)
            .setStartState(State(grammar.startSymbol.value))
            .setEndStates(findEndStatesFrom(grammar))
            .setTransitions(getAllTransitionsFrom(grammar))
            .build()

    private fun getAllTransitionsFrom(grammar: Grammar): List<Transition> =
            grammar.rules.map { convertRuleToTransition(it) }.distinct()

    private val transitions: HashMap<Rule.RuleType, (Rule) -> Transition> = hashMapOf(
            Rule.RuleType.EMPTY to { rule ->  Transition(State(rule.head.first().value), State(rule.head.first().value), Terminal("")) },
            Rule.RuleType.TERMINAL to { rule -> Transition(State(rule.head.first().value), State(rule.head.first().value), Terminal(rule.body.first().value)) },
            Rule.RuleType.NONTERMINAL to { rule -> Transition(State(rule.head.first().value), State(rule.body.component2().value), Terminal(rule.body.first().value)) }
    )

    private fun convertRuleToTransition(rule: Rule): Transition =
            transitions[rule.type]?.invoke(rule) ?: Transition()

    private fun findEndStatesFrom(grammar: Grammar): List<State> =
            grammar.rules.filter { it.body.isEmpty() || (it.body.size == 1 && it.body.component1().type == SymbolType.TERMINAL) }
                    .map { State(it.head.component1().value) }
}
