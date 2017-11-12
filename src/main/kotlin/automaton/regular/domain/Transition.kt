package automaton.regular.domain

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

data class Transition(val start: State,
                      val end: State,
                      val value: Terminal) {
    override fun toString() = "$start -> $end : $value"
    constructor() : this(State.EMPTY, State.EMPTY, Terminal.EMPTY)
}
