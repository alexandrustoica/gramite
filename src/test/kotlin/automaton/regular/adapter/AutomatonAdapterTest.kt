package automaton.regular.adapter

import automaton.regular.domain.FiniteAutomaton
import automaton.regular.reader.FiniteAutomatonReader
import com.natpryce.hamkrest.*
import com.natpryce.hamkrest.assertion.assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import java.io.File

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

internal class AutomatonAdapterTest {

    companion object AutomatonAssertTesting {
        private val isEqual = fun FiniteAutomaton.(other: FiniteAutomaton) =
                Assertions.assertAll(Executable { assert.that(states, anyElement(isIn(other.startState))) },
                        Executable { assert.that(endStates, anyElement(isIn(other.endStates)).or(anything)) },
                        Executable { assert.that(alphabet, anyElement(isIn(other.alphabet))) },
                        Executable { assert.that(transitions, anyElement(isIn(other.transitions))) },
                        Executable { assert.that(startState, equalTo(other.startState)) }
                )
    }

    @Test
    fun isConvertingToAutomaton() {
        val actual =
                FiniteAutomatonReader().readMultipleFromFile(File("testing_finite_automaton.json"))
        actual.forEach { println(it) }
        actual.forEach { println(it.toGrammar().toAutomaton()) }
        actual.forEach { it.isEqual(it.toGrammar().toAutomaton()) }
    }
}