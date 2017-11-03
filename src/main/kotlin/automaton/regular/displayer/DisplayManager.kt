package automaton.regular.displayer

import java.util.*

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

open class DisplayerManager : Displayer {

    interface DisplayOption
    private enum class EmptyOption : DisplayOption

    open protected val optionMessage: HashMap<DisplayOption, String> = hashMapOf()
    open protected val action: HashMap<DisplayOption, () -> Unit> = hashMapOf()

    private fun displayMenu() =
            optionMessage.values.sorted().forEach { println(it) }

    open protected val getUserOptionFrom =
            fun(index: Int): DisplayOption = EmptyOption.values()[index - 1]

    private fun executeOption(option: DisplayOption)
            = action[option]?.invoke()

    private fun read(): Int =
            print("Option: ").let { Scanner(System.`in`).nextInt() }

    override fun start() =
            displayMenu().also { read().let { executeOption(getUserOptionFrom(it)) } }

}