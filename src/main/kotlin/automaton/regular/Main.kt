package automaton.regular

import automaton.regular.reader.FiniteAutomatonReader
import automaton.regular.reader.GrammarReader
import java.io.File

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

fun main(args: Array<String>) {
    val grammarsFromFile =
            GrammarReader().readMultipleFromFile(File("testing_grammars.json"))
    grammarsFromFile.forEach { println(it) }
    val automatonsFromFile =
            FiniteAutomatonReader().readMultipleFromFile(File("testing_finite_automaton.json"))
    automatonsFromFile.forEach { println(it) }
}