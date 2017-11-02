package automata.regular

import automata.regular.displayer.GrammarDisplayer
import automata.regular.reader.GrammarReader
import java.io.File

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


fun main(args: Array<String>) {
   val grammar = GrammarReader().readFromFile(File("grammar.json"))
    GrammarDisplayer(grammar).start()
}