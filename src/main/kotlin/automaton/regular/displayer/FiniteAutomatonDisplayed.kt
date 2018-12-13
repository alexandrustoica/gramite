package automaton.regular.displayer

import automaton.regular.domain.FiniteAutomaton
import automaton.regular.domain.Transition
import java.util.*


class FiniteAutomatonDisplayed(private val automaton: FiniteAutomaton) : DisplayedManager() {

    enum class Option : DisplayOption {
        STATES, ALPHABET, FINAL, TRANSITIONS, EXIT
    }

    override val getUserOptionFrom = fun(index: Int): DisplayOption = Option.values()[index - 1]

    override val optionMessage: HashMap<DisplayOption, String> = hashMapOf(
            Option.STATES to "1. Display the set of states.",
            Option.ALPHABET to "2. Display the alphabet.",
            Option.FINAL to "3. Display the set of final states.",
            Option.TRANSITIONS to "4. Display the transitions.",
            Option.EXIT to "5. Exit")

    override val action: HashMap<DisplayOption, () -> Unit> = hashMapOf(
            Option.STATES to { print("States: ").let { printList(automaton.states).also { start() } } },
            Option.ALPHABET to { print("Alphabet: ").let { printList(automaton.alphabet).also { start() } } },
            Option.FINAL to { print("Final States: ").let { printList(automaton.endStates) }.also { start() } },
            Option.TRANSITIONS to { printTransitions(automaton.transitions).also { start() } },
            Option.EXIT to { print("Done") }
    )

    private fun printTransitions(list: List<Transition>) =
            list.forEachIndexed { index, value -> println("Transition#$index $value ") }.let { println() }

    private fun <T> printList(list: List<T>) =
            list.forEach { print("$it ") }.let { println() }
}
