package automaton.regular.reader

import java.io.File


interface FileReader<out T> {
    fun readFromFile(source: File): T
}
