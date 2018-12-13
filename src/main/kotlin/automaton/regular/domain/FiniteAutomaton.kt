package automaton.regular.domain

import automaton.regular.adapter.GrammarAdapter


data class FiniteAutomaton(
        val states: List<State>,
        val startState: State,
        val endStates: List<State>,
        val alphabet: List<Terminal>,
        val transitions: List<Transition>) {

    fun getTransitionsBasedOn(end: State) =
            transitions.filter { it.end == end }

    fun toGrammar() = GrammarAdapter(this).toGrammar()

}

