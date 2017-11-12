package automaton.regular.adapter

import automaton.regular.domain.FiniteAutomaton
import automaton.regular.domain.Grammar
import automaton.regular.domain.Rule
import automaton.regular.domain.Transition

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

class GrammarAdapter(private val automaton: FiniteAutomaton) {

    fun toGrammar() =
            Grammar(automaton.alphabet, nonTerminals(), rules(), automaton.startState.toNonTerminal())

    private fun rules(): List<Rule> = listOf()

    private fun convertTransitionsToRules(transitions: List<Transition>) =
            transitions.map { convertTransitionToRule(it) }

    private fun convertTransitionToRule(transition: Transition) =
            Rule(listOf(transition.start.toNonTerminal()),
                    listOf(transition.value) + filterTerminalState(transition))

    private fun filterTerminalState(transition: Transition) =
            if(transition.end.value.matches(Regex("W\\d*"))) listOf()
            else listOf(transition.end.toNonTerminal())

    private fun nonTerminals() =
            automaton.states.filterNot { it.value.matches(Regex("W\\d*")) }.map { it.toNonTerminal() }
}