package automaton.regular.reader

import java.io.File

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

interface FileReader<out T> {
    fun readFromFile(source: File): T
}
