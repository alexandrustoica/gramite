package automaton.regular.adapter

import automaton.regular.builder.FiniteAutomatonBuilder
import automaton.regular.builder.GrammarBuilder
import automaton.regular.domain.FiniteAutomaton
import automaton.regular.domain.Grammar
import automaton.regular.domain.NonTerminal
import automaton.regular.domain.State

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
            .build()
}
