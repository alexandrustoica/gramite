package automaton.regular

import automaton.regular.adapter.Converter
import automaton.regular.reader.FiniteAutomatonReader
import automaton.regular.reader.GrammarReader
import java.io.File

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


fun main(args: Array<String>) {
    val grammarFromFile = GrammarReader().readFromFile(File("grammar.json"))
    grammarFromFile.isRegular()
    val automatonFromFile = FiniteAutomatonReader().readFromFile(File("finite_automaton.json"))
    Converter().toGrammar(automatonFromFile)
}