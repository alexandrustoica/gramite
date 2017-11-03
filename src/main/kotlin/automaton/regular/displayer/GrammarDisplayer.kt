package automaton.regular.displayer

import automaton.regular.domain.Grammar
import automaton.regular.domain.NonTerminal
import automaton.regular.domain.Rule
import automaton.regular.domain.Symbol
import java.util.*

/**
 * @author Alexandru Stoica
 * @version 1.0
 */



class GrammarDisplayer(private val grammar: Grammar) : DisplayerManager() {

    enum class Option : DisplayOption {
        TERMINAL, NONTERMINAL, RULES, RULES_NONTERMINAL, EXIT
    }

    override val optionMessage: HashMap<DisplayOption, String> = hashMapOf(
            Option.TERMINAL to "1. Display the set of terminals.",
            Option.NONTERMINAL to "2. Display the set of non-terminals.",
            Option.RULES to "3. Display the set of rules.",
            Option.RULES_NONTERMINAL to "4. Display the set of rules for a given non-terminal.",
            Option.EXIT to "5. Exit")

    override val action: HashMap<DisplayOption, () -> Unit> = hashMapOf(
            Option.TERMINAL to { print("Terminals: ").let { printValues(grammar.terminals).also { start() } } },
            Option.NONTERMINAL to { print("NonTerminals: ").let { printValues(grammar.nonTerminals).also { start() } } },
            Option.RULES to { print("NonTerminals: ").let { printRules(grammar.rules) }.also { start() } },
            Option.RULES_NONTERMINAL to { printRules(grammar.getRulesFor(readNonTerminal())).also { start() } },
            Option.EXIT to { print("Done") }
    )

    override val getUserOptionFrom = fun(index: Int): DisplayOption = Option.values()[index - 1]

    private fun readNonTerminal() =
            print("NonTerminal:").let { NonTerminal(readLine().orEmpty()) }

    private fun printRules(rules: List<Rule>) =
            rules.forEachIndexed { index, rule -> println("Rule#$index: $rule") }

    private fun printValues(list: List<Symbol>) =
            list.forEach { print("$it ") }.let { println() }
}