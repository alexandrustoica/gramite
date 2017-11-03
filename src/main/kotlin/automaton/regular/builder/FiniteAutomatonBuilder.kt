package automaton.regular.builder

import automaton.regular.domain.FiniteAutomaton
import automaton.regular.domain.State
import automaton.regular.domain.Terminal
import automaton.regular.domain.Transition

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

data class FiniteAutomatonBuilder(
        private val states: List<State>,
        private val startState: State,
        private val endStates: List<State>,
        private val alphabet: List<Terminal>,
        private val transitions: List<Transition>) {

    constructor() : this(listOf(), State(""), listOf(), listOf(), listOf())

    fun setStates(states: List<State>): FiniteAutomatonBuilder =
            FiniteAutomatonBuilder(states, startState, endStates, alphabet, transitions)

    fun setStartState(startState: State): FiniteAutomatonBuilder =
            FiniteAutomatonBuilder(states, startState, endStates, alphabet, transitions)

    fun setEndStates(endStates: List<State>): FiniteAutomatonBuilder =
            FiniteAutomatonBuilder(states, startState, endStates, alphabet, transitions)

    fun setAlphabet(alphabet: List<Terminal>): FiniteAutomatonBuilder =
            FiniteAutomatonBuilder(states, startState, endStates, alphabet, transitions)

    fun setTransitionsFromStrings(transitionsAsStrings: List<String>): FiniteAutomatonBuilder =
            FiniteAutomatonBuilder(states, startState, endStates, alphabet,
                    convertStringsToTransitions(transitionsAsStrings))

    fun setTransitions(transitions: List<Transition>): FiniteAutomatonBuilder =
            FiniteAutomatonBuilder(states, startState, endStates, alphabet, transitions)

    fun build() = FiniteAutomaton(states, startState, endStates, alphabet, transitions)

    private fun convertStringsToTransitions(strings: List<String>): List<Transition> =
            strings.map { convertStringToTransition(it) }

    private fun convertStringToTransition(string: String): Transition =
        string.replace(" ", "").split("->", ":").let {
            Transition(State(it.component1()), State(it.component2()), Terminal(it.component3()))
        }
}