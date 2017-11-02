package automata.regular.displayer

import automata.regular.domain.Grammar
import automata.regular.domain.NonTerminal
import automata.regular.domain.Rule
import automata.regular.domain.Symbol
import java.util.*

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


class GrammarDisplayer(private val grammar: Grammar) : Displayer {

    enum class Option {
        TERMINAL, NONTERMINAL, RULES, RULES_NONTERMINAL, EXIT
    }

    private val optionMessage: HashMap<Option, String> = hashMapOf(
            Option.TERMINAL to "1. Display the set of terminals.",
            Option.NONTERMINAL to "2. Display the set of non-terminals.",
            Option.RULES to "3. Display the set of rules.",
            Option.RULES_NONTERMINAL to "4. Display the set of rules for a given non-terminal.",
            Option.EXIT to "5. Exit")

    private val displayMenu: Unit = optionMessage.values.sorted().forEach { println(it) }
    private val getUserOptionFrom = fun(index: Int): Option = Option.values()[index - 1]
    private val executeOption = fun(option: Option) = action[option]?.invoke()

    private fun read(): Int = print("Option: ").let { Scanner(System.`in`).nextInt() }

    private fun readNonTerminal() =
            print("NonTerminal:").let { NonTerminal(readLine().orEmpty()) }

    private fun printRules(rules: List<Rule>) =
            rules.forEachIndexed { index, rule -> println("Rule#$index: $rule") }

    private fun printValues(list: List<Symbol>) =
            list.forEach { print("${it.value} ") }.let { println() }

    private val action: HashMap<Option, () -> Unit> = hashMapOf(
            Option.TERMINAL to { print("Terminals: ").let { printValues(grammar.terminals).also { start() } } },
            Option.NONTERMINAL to { print("NonTerminals: ").let { printValues(grammar.nonTerminals).also { start() } } },
            Option.RULES to { print("NonTerminals: ").let { printRules(grammar.rules) }.also { start() } },
            Option.RULES_NONTERMINAL to { printRules(grammar.getRulesFor(readNonTerminal())).also { start() } },
            Option.EXIT to { print("Done") }
    )

    override fun start() =
            displayMenu.also { read().let { executeOption(getUserOptionFrom(it)) } }
}