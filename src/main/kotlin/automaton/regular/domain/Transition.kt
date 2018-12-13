package automaton.regular.domain


data class Transition(val start: State,
                      val end: State,
                      val value: Terminal) {
    override fun toString() = "$start -> $end : $value"
}

