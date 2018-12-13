package automaton.regular

import automaton.regular.displayer.FiniteAutomatonDisplayed
import automaton.regular.displayer.GrammarDisplayed
import automaton.regular.reader.FiniteAutomatonReader
import automaton.regular.reader.GrammarReader
import java.io.File


fun main(args: Array<String>) {
    val grammarFile = File({}.javaClass
            .getResource("/testing_grammars.json").path)
    val finiteAutomatonFile = File({}.javaClass
            .getResource("/testing_finite_automaton.json").path)
    val grammarsFromFile = GrammarReader()
            .readMultipleFromFile(grammarFile)
    val automatonsFromFile = FiniteAutomatonReader()
            .readMultipleFromFile(finiteAutomatonFile)
    GrammarDisplayed(grammarsFromFile[0]).start()
    FiniteAutomatonDisplayed(automatonsFromFile[0]).start()
}