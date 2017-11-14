package automaton.regular.reader

import automaton.regular.builder.GrammarBuilder
import automaton.regular.domain.Grammar
import automaton.regular.domain.NonTerminal
import automaton.regular.domain.Terminal
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.array
import java.io.File

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


class GrammarReader : Reader<Grammar>, FileReader<Grammar> {

    companion object GrammarJsonExtension {
        private val toGrammar = fun JsonObject.(): Grammar = GrammarBuilder()
                .setTerminals(this.array<String>("terminals")?.value?.toList().orEmpty().map { Terminal(it) })
                .setNonTerminals(this.array<String>("nonTerminals")?.value?.toList().orEmpty().map { NonTerminal(it) })
                .setStartSymbol(NonTerminal(this.getValue("startSymbol") as String))
                .setRules(this.array<String>("rules")?.value?.toList().orEmpty())
                .build()
    }

    override fun readFromFile(source: File): Grammar =
            getJsonParserFor(source).toGrammar()

    fun readMultipleFromFile(source: File): List<Grammar> =
            getJsonParserForFileList(source).map { it.toGrammar() }

    private fun getJsonParserFor(source: File): JsonObject =
            Parser().parse(StringBuilder(source.readText())) as JsonObject

    private fun getJsonParserForFileList(source: File): JsonArray<JsonObject> =
            Parser().parse(StringBuilder(source.readText())) as JsonArray<JsonObject>

    override fun read(): Grammar = GrammarBuilder()
            .setTerminals(readTerminals())
            .setNonTerminals(readNonTerminals())
            .setStartSymbol(readStartSymbol())
            .setRules(readRules()).build()

    private fun readStartSymbol(): NonTerminal =
            print("Start Symbol: ").let { readLine()?.replace(" ", "") ?: "" }.let { NonTerminal(it) }

    private fun readTerminals(): List<Terminal> =
            readElementsBasedOn("Terminals", ",").map { Terminal(it) }

    private fun readNonTerminals(): List<NonTerminal> =
            readElementsBasedOn("NonTerminals", ",").map { NonTerminal(it) }

    private fun readElementsBasedOn(message: String, delimiters: String): List<String> =
            print("$message: ").let { readLine()?.replace(" ", "")?.split(delimiters) ?: listOf() }

    private fun readRules(): List<String> =
            print("Number of Rules: ").let { readRules(0, mutableListOf(), readLine()?.toInt() ?: 0) }

    private fun readRules(indexRule: Int, accumulator: MutableList<String>, total: Int): List<String> =
            if (indexRule == total) accumulator.toList()
            else print("Rule#${indexRule + 1}: ").let { addToAccumulatorAndContinue(indexRule + 1, accumulator, total) }

    private fun addToAccumulatorAndContinue(indexRule: Int, accumulator: MutableList<String>, total: Int): List<String> =
            readRules(indexRule, accumulator.add(readLine().orEmpty()).let { accumulator }, total)
}