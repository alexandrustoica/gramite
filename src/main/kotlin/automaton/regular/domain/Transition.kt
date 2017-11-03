package automaton.regular.domain

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

data class Transition(private val start: State,
                      private val end: State,
                      private val value: Terminal) {
    override fun toString() = "$start -> $end : $value"
}
