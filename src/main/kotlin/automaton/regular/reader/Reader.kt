package automaton.regular.reader

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

interface Reader<out T> {
    fun read(): T
}
