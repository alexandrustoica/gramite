package automaton.regular.domain

import automaton.regular.adapter.GrammarAdapter

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

data class FiniteAutomaton(val states: List<State>,
                           val startState: State,
                           val endStates: List<State>,
                           val alphabet: List<Terminal>,
                           val transitions: List<Transition>) {
    fun toGrammar() = GrammarAdapter(this).toGrammar()
}

